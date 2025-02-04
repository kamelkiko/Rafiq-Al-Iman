package com.kamel.rafiq_al_iman

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.kamel.rafiq_al_iman.data.util.localizedMessages.LocalMessageFactory
import com.kamel.rafiq_al_iman.di.koin.KoinComponent
import com.kamel.rafiq_al_iman.di.koin.inject
import com.kamel.rafiq_al_iman.di.koin.startKoin
import com.kamel.rafiq_al_iman.di.module.appModule
import com.kamel.rafiq_al_iman.domain.util.MessageSender
import com.kamel.rafiq_al_iman.domain.util.MessageSenderImpl
import com.kamel.rafiq_al_iman.presentation.service.PrayerTimesService
import com.kamel.rafiq_al_iman.presentation.util.showAppPrayerDialog
import com.kamel.rafiq_al_iman.presentation.util.showNotification
import com.kamel.rafiq_al_iman.util.*
import java.io.File

class AppStartupActivity : ProjectActivity, KoinComponent {
    private val localMessageFactory: LocalMessageFactory by inject<LocalMessageFactory>()
    private val messageSender: MessageSender by inject<MessageSenderImpl>()

    init {
        startKoin(appModule)
        verifyDatabaseAccess()
    }

    override suspend fun execute(project: Project) {
        val localMessages = localMessageFactory.createLocalizedMessages(Constant.ARABIC_CODE)

        val prayerTimesService = project.getService(PrayerTimesService::class.java)

        prayerTimesService.schedulePrayerNotifications(messageSender = messageSender)

        val currentTime = getCurrentTime().formatWith12HourClock()
        val currentPrayer = prayerTimesService.getCurrentPrayer(currentTime)
        val currentPrayerName = getPrayerNameByArabic(currentPrayer ?: "")

        showAppPrayerDialog(
            message = "${localMessages.didYouPray} $currentPrayerName؟",
            title = localMessages.prayerReminder,
            data = ayat.shuffled().random(),
        )
    }

    private fun verifyDatabaseAccess() {
        val dbFile = File(AppPaths.getDatabasePath())
        if (!dbFile.exists()) {
            dbFile.createNewFile() // Create empty DB file if missing
        }

        if (!dbFile.canWrite()) {
            showNotification("Error", "No write permissions for database file: ${dbFile.absolutePath}")
            throw IllegalStateException("No write permissions for database file: ${dbFile.absolutePath}")
        }
    }
}