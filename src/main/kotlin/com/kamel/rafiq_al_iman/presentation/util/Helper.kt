package com.kamel.rafiq_al_iman.presentation.util

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.util.application
import com.intellij.util.ui.UIUtil
import com.kamel.rafiq_al_iman.presentation.ui.AppPrayerDialog
import com.kamel.rafiq_al_iman.presentation.ui.ZekrDialog

fun showZekrDialog(data: List<String>) {
    application.invokeLater {
        val dialog = ZekrDialog(data)
        dialog.show()
    }
}

fun showAppPrayerDialog(message: String, title: String, data: String) {
    application.invokeLater {
        val dialog = AppPrayerDialog(message, data, title)
        dialog.show()
    }
}

fun showNotification(title: String, message: String, type: NotificationType = NotificationType.INFORMATION) {
    val notification = Notification(
        "com.kamel.rafiq_al_iman", // Group ID
        title, // Notification title
        message, // Notification content
        type // Notification type
    )
    Notifications.Bus.notify(notification)
}

fun playAudio(resourcePath: String) {
    try {
        UIUtil.playSoundFromResource(resourcePath)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}