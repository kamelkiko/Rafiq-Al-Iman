package com.kamel.rafiq_al_iman.presentation.listener

import com.android.tools.idea.gradle.project.sync.GradleSyncListenerWithRoot
import com.intellij.openapi.project.Project
import com.kamel.rafiq_al_iman.presentation.service.ZekrService

class SyncListener : GradleSyncListenerWithRoot {

    override fun syncFailed(project: Project, errorMessage: String, rootProjectPath: String) {
        super.syncFailed(project, errorMessage, rootProjectPath)
        val zekrService: ZekrService = project.getService(ZekrService::class.java)
        zekrService.startShowingRandomIstkhfar()
    }

    override fun syncCancelled(project: Project, rootProjectPath: String) {
        super.syncCancelled(project, rootProjectPath)
        val zekrService: ZekrService = project.getService(ZekrService::class.java)
        zekrService.startShowingRandomIstkhfar()
    }

    override fun syncSkipped(project: Project) {
        super.syncSkipped(project)
        val zekrService: ZekrService = project.getService(ZekrService::class.java)
        zekrService.startShowingRandomHamd()
    }

    override fun syncStarted(project: Project, rootProjectPath: String) {
        super.syncStarted(project, rootProjectPath)
        val zekrService: ZekrService = project.getService(ZekrService::class.java)
        zekrService.startShowingRandomZekr()
        zekrService.startShowingRandomTasbeh()
    }

    override fun syncSucceeded(project: Project, rootProjectPath: String) {
        super.syncSucceeded(project, rootProjectPath)
        val zekrService: ZekrService = project.getService(ZekrService::class.java)
        zekrService.startShowingRandomHamd()
    }
}