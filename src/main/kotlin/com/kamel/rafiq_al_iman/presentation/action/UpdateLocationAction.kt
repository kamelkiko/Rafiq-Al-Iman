package com.kamel.rafiq_al_iman.presentation.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.kamel.rafiq_al_iman.di.koin.inject
import com.kamel.rafiq_al_iman.domain.util.MessageSender
import com.kamel.rafiq_al_iman.domain.util.MessageSenderImpl
import com.kamel.rafiq_al_iman.presentation.service.PrayerTimesService

class UpdateLocationAction : AnAction() {
    private val messageSender: MessageSender by inject<MessageSenderImpl>()

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val prayerTimesService = project.getService(PrayerTimesService::class.java)
        prayerTimesService.updateLocation(messageSender = messageSender)
    }
}