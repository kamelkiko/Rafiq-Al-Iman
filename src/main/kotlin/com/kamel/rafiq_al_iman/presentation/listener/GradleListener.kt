package com.kamel.rafiq_al_iman.presentation.listener

import com.android.tools.idea.gradle.project.build.BuildContext
import com.android.tools.idea.gradle.project.build.BuildStatus
import com.android.tools.idea.gradle.project.build.GradleBuildListener
import com.kamel.rafiq_al_iman.util.hamd
import com.kamel.rafiq_al_iman.util.istkhfar
import com.kamel.rafiq_al_iman.util.tasbeh
import com.kamel.rafiq_al_iman.presentation.service.ZekrService
import com.kamel.rafiq_al_iman.presentation.util.showNotification

class GradleListener : GradleBuildListener {

    override fun buildStarted(context: BuildContext) {
        val zekrService: ZekrService = context.project.getService(ZekrService::class.java)
        zekrService.startShowingRandomZekr()
        zekrService.startShowingRandomTasbeh()
    }

    override fun buildFinished(status: BuildStatus, context: BuildContext?) {
        val zekrService: ZekrService? = context?.project?.getService(ZekrService::class.java)
        when (status) {
            BuildStatus.SUCCESS -> zekrService?.startShowingRandomHamd() ?: run {
                val randomHamd = hamd.random()
                showNotification("", randomHamd)
            }

            BuildStatus.FAILED -> zekrService?.startShowingRandomIstkhfar() ?: run {
                val randomIstkhar = istkhfar.random()
                showNotification("", randomIstkhar)
            }

            else -> zekrService?.startShowingRandomIstkhfar() ?: run {
                val randomTasbeh = tasbeh.random()
                showNotification("", randomTasbeh)
            }
        }
    }
}