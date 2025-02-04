package com.kamel.rafiq_al_iman.presentation.listener

import com.intellij.compiler.server.BuildManagerListener
import com.intellij.openapi.project.Project
import com.kamel.rafiq_al_iman.presentation.service.ZekrService
import java.util.*

class BuildListener : BuildManagerListener {

    override fun buildStarted(project: Project, sessionId: UUID, isAutomake: Boolean) {
        super.buildStarted(project, sessionId, isAutomake)
        val zekrService: ZekrService = project.getService(ZekrService::class.java)
        zekrService.startShowingRandomZekr()
        zekrService.startShowingRandomTasbeh()
    }

    override fun buildFinished(project: Project, sessionId: UUID, isAutomake: Boolean) {
        super.buildFinished(project, sessionId, isAutomake)
        val zekrService: ZekrService = project.getService(ZekrService::class.java)
        zekrService.startShowingRandomHamd()
    }
}