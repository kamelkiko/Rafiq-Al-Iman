package com.kamel.rafiq_al_iman.domain.util

interface MessageSender {
    fun showNotificationBeforePrayerTime(prayerName: String)
    fun showMessageOnPrayerTime(prayerName: String)
}