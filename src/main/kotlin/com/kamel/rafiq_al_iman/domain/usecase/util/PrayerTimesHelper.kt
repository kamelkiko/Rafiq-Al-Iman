package com.kamel.rafiq_al_iman.domain.usecase.util

import com.kamel.rafiq_al_iman.domain.entity.Month
import com.kamel.rafiq_al_iman.domain.entity.PrayerTimeName
import com.kamel.rafiq_al_iman.domain.entity.PrayerYearlyTiming
import com.kamel.rafiq_al_iman.util.formatWith12HourClock
import java.time.LocalDate
import java.time.LocalTime

class PrayerTimesHelper {

    fun getCurrentPrayerTimes(
        prayerYearlyTiming: PrayerYearlyTiming,
        currentDate: LocalDate
    ): Map<String, String> {
        val currentMonth = currentDate.monthValue
        val currentDayIndex = currentDate.dayOfMonth.minus(1) // Zero-based index

        val prayerTimes = prayerYearlyTiming

        val currentMonthTimes = when (currentMonth) {
            Month.JANUARY.value -> prayerTimes.januaryTimes
            Month.FEBRUARY.value -> prayerTimes.februaryTimes
            Month.MARCH.value -> prayerTimes.marchTimes
            Month.APRIL.value -> prayerTimes.aprilTimes
            Month.MAY.value -> prayerTimes.mayTimes
            Month.JUNE.value -> prayerTimes.juneTimes
            Month.JULY.value -> prayerTimes.julyTimes
            Month.AUGUST.value -> prayerTimes.augustTimes
            Month.SEPTEMBER.value -> prayerTimes.septemberTimes
            Month.OCTOBER.value -> prayerTimes.octoberTimes
            Month.NOVEMBER.value -> prayerTimes.novemberTimes
            Month.DECEMBER.value -> prayerTimes.decemberTimes
            else -> throw IllegalArgumentException("Invalid month: $currentMonth")
        }

        val currentDayTimings = currentMonthTimes.getOrNull(currentDayIndex)
            ?: throw IllegalStateException("No prayer times found for the current day")

        return mapOf(
            PrayerTimeName.FAJR.value to currentDayTimings.fajr.split(" ")[TIME],
            PrayerTimeName.SUNRISE.value to currentDayTimings.sunrise.split(" ")[TIME],
            PrayerTimeName.DHUHR.value to currentDayTimings.dhuhr.split(" ")[TIME],
            PrayerTimeName.ASR.value to currentDayTimings.asr.split(" ")[TIME],
            PrayerTimeName.MAGHRIB.value to currentDayTimings.maghrib.split(" ")[TIME],
            PrayerTimeName.ISHA.value to currentDayTimings.isha.split(" ")[TIME],
        )
    }

    fun formatPrayerTimesWith12HourClock(prayerTimes: Map<String, String>): Map<String, String> {
        return prayerTimes.mapValues { (_, time) ->
            LocalTime.parse(time).formatWith12HourClock()
        }
    }

    private companion object {
        private const val TIME = 0
    }
}