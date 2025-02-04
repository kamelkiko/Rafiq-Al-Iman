package com.kamel.rafiq_al_iman.domain.util

import com.intellij.notification.NotificationType
import com.intellij.util.application
import com.kamel.rafiq_al_iman.data.util.localizedMessages.LocalMessageFactory
import com.kamel.rafiq_al_iman.di.koin.KoinComponent
import com.kamel.rafiq_al_iman.di.koin.inject
import com.kamel.rafiq_al_iman.presentation.util.playAudio
import com.kamel.rafiq_al_iman.presentation.util.showAppPrayerDialog
import com.kamel.rafiq_al_iman.presentation.util.showNotification
import com.kamel.rafiq_al_iman.util.Constant
import com.kamel.rafiq_al_iman.util.Res
import com.kamel.rafiq_al_iman.util.ayat

class MessageSenderImpl : MessageSender, KoinComponent {
    private val localMessageFactory by inject<LocalMessageFactory>()
    private val message = localMessageFactory.createLocalizedMessages(Constant.ARABIC_CODE)

    override fun showNotificationBeforePrayerTime(prayerName: String) {
        showNotification(
            prayerName,
            "${message.beforeAzanTimeCome} $prayerName",
            NotificationType.WARNING
        )
    }

    override fun showMessageOnPrayerTime(prayerName: String) {
        Thread {
            playAudio(Res.PRAY_NOW_SOUND)
        }.start()
        application.invokeLater {
            showAppPrayerDialog(
                message = "${message.timeToPray} $prayerName!",
                title = "${message.pray} $prayerName",
                data = ayat.shuffled().random()
            )
        }
    }
}