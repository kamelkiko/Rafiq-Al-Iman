package com.kamel.rafiq_al_iman.domain.usecase

import com.kamel.rafiq_al_iman.domain.usecase.util.PrayerTimesHelper
import com.kamel.rafiq_al_iman.domain.util.MessageSender
import com.kamel.rafiq_al_iman.util.formatWith12HourClock
import com.kamel.rafiq_al_iman.util.getCurrentDate
import com.kamel.rafiq_al_iman.util.getCurrentTime
import com.kamel.rafiq_al_iman.util.getPrayerNameByArabic
import java.time.LocalTime
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class SchedulePrayerNotificationsUseCase(
    private val getPrayerTimesUseCase: GetPrayerTimesUseCase,
    private val prayerTimesHelper: PrayerTimesHelper,
) {
    private var scheduler: ScheduledExecutorService? = null

    suspend operator fun invoke(messageSender: MessageSender) {
        cancelExistingTasks()

        val currentDate = getCurrentDate()
        val currentYear = currentDate.year.toString()

        val prayerTimes = getPrayerTimesUseCase.invoke(currentYear)

        val prayerTimesMap = prayerTimesHelper.getCurrentPrayerTimes(prayerTimes, currentDate)

        scheduleBeforePrayerTimeNotifications(prayerTimesMap, messageSender::showNotificationBeforePrayerTime)
        schedulePrayerTimeNotifications(prayerTimesMap, messageSender::showMessageOnPrayerTime)
    }

    private fun cancelExistingTasks() {
        scheduler?.let { executor ->
            executor.shutdownNow()
            try {
                // Wait for the executor to terminate
                if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                    executor.shutdownNow()
                } else {
                }
            } catch (e: InterruptedException) {
                executor.shutdownNow()
            }
        }
    }

    private fun schedulePrayerTimeNotifications(
        prayerTimes: Map<String, String>,
        showMessageOnPrayerTime: (String) -> Unit,
    ) {
        schedulePrayerNotifications(
            prayerTimes,
            timeAdjustment = { it },
            notificationMessageProvider = { prayerName ->
                showMessageOnPrayerTime(prayerName)
            },
            false
        )
    }

    private fun scheduleBeforePrayerTimeNotifications(
        prayerTimes: Map<String, String>,
        showNotificationBeforePrayerTime: (String) -> Unit,
    ) {
        schedulePrayerNotifications(
            prayerTimes,
            timeAdjustment = { it.minusMinutes(WARNING_BEFORE_5_MINUTES) }, // Adjust by subtracting 5 minutes
            notificationMessageProvider = { prayerName ->
                showNotificationBeforePrayerTime(prayerName)
            },
            true
        )
    }

    private fun schedulePrayerNotifications(
        prayerTimes: Map<String, String>,
        timeAdjustment: (LocalTime) -> LocalTime,
        notificationMessageProvider: (String) -> Unit,
        isBefore: Boolean,
    ) {
        if (scheduler == null || scheduler!!.isShutdown) {
            scheduler = Executors.newScheduledThreadPool(CORE_POOL_SIZE)
        }
        prayerTimes.forEach { (_prayerName, prayerTime) ->
            val prayerName = getPrayerNameByArabic(_prayerName)
            val targetTime = timeAdjustment(LocalTime.parse(prayerTime))
            val currentTime = getCurrentTime()
            (1..5).forEach { minute ->
                if (LocalTime.parse(prayerTime).minusMinutes(minute.toLong())
                        .formatWith12HourClock() == currentTime.formatWith12HourClock() && isBefore
                )
                    notificationMessageProvider(prayerName)
            }
            if (targetTime.formatWith12HourClock() == currentTime.formatWith12HourClock() && isBefore.not())
                notificationMessageProvider(prayerName)
            val initialDelay = calculateDelay(currentTime, targetTime)
            scheduler?.schedule({
                notificationMessageProvider(prayerName)
            }, initialDelay, TimeUnit.SECONDS)
        }
    }

    private fun calculateDelay(currentTime: LocalTime, targetTime: LocalTime): Long {
        val currentSeconds = currentTime.toSecondOfDay()
        val targetSeconds = targetTime.toSecondOfDay()
        val secondsInADay = 86400
        return if (targetSeconds > currentSeconds) {
            (targetSeconds - currentSeconds).toLong()
        } else {
            (secondsInADay + targetSeconds - currentSeconds).toLong()
        }
    }

    private companion object {
        private const val CORE_POOL_SIZE = 1
        private const val WARNING_BEFORE_5_MINUTES = 5L
    }
}