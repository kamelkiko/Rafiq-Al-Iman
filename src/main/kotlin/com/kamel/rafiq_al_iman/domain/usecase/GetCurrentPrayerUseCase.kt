package com.kamel.rafiq_al_iman.domain.usecase

import com.kamel.rafiq_al_iman.domain.usecase.util.PrayerTimesHelper
import com.kamel.rafiq_al_iman.util.getCurrentDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class GetCurrentPrayerUseCase(
    private val getPrayerTimesUseCase: GetPrayerTimesUseCase,
    private val prayerTimesHelper: PrayerTimesHelper,
) {
    suspend operator fun invoke(currentTime: String): String? {
        val currentDate = getCurrentDate()
        val currentYear = currentDate.year.toString()

        val prayerTimes = getPrayerTimesUseCase.invoke(currentYear)

        val prayerTimesMap = prayerTimesHelper.getCurrentPrayerTimes(prayerTimes, currentDate)

        val formattedPrayerTimes = PrayerTimesHelper().formatPrayerTimesWith12HourClock(prayerTimesMap)

        val sortedPrayerTimes =
            formattedPrayerTimes.toList().sortedBy { LocalTime.parse(it.second, formatWith12HourClock) }

        for (i in sortedPrayerTimes.indices) {
            val (prayerName, prayerTime) = sortedPrayerTimes[i]
            val nextPrayerTime = if (i < sortedPrayerTimes.size - 1) {
                sortedPrayerTimes[i + 1].second
            } else {
                sortedPrayerTimes[TIME].second
            }

            if (isTimeBetween(currentTime, prayerTime, nextPrayerTime)) {
                return prayerName
            }
        }

        return null
    }

    private fun isTimeBetween(currentTime: String, startTime: String, endTime: String): Boolean {
        val current = LocalTime.parse(currentTime, formatWith12HourClock)
        val start = LocalTime.parse(startTime, formatWith12HourClock)
        val end = LocalTime.parse(endTime, formatWith12HourClock)

        return if (start.isBefore(end)) {
            current >= start && current < end
        } else {
            current >= start || current < end
        }
    }

    private companion object {
        private const val PATTERN_12_HOUR_CLOCK = "hh:mm a"
        private val formatWith12HourClock = DateTimeFormatter.ofPattern(PATTERN_12_HOUR_CLOCK)
        private const val TIME = 0
    }
}