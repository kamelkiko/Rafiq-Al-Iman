package com.kamel.rafiq_al_iman.presentation.service

import com.intellij.openapi.Disposable
import com.intellij.openapi.components.Service
import com.kamel.rafiq_al_iman.di.koin.KoinComponent
import com.kamel.rafiq_al_iman.di.koin.inject
import com.kamel.rafiq_al_iman.domain.usecase.GetCurrentPrayerUseCase
import com.kamel.rafiq_al_iman.domain.usecase.SchedulePrayerNotificationsUseCase
import com.kamel.rafiq_al_iman.domain.usecase.UpdateLocationUseCase
import com.kamel.rafiq_al_iman.domain.util.MessageSender
import com.kamel.rafiq_al_iman.presentation.util.showNotification
import kotlinx.coroutines.*

@Service(Service.Level.PROJECT)
class PrayerTimesService : Disposable, KoinComponent {
    private val schedulePrayerNotificationsUseCase by inject<SchedulePrayerNotificationsUseCase>()
    private val updateLocationUseCase by inject<UpdateLocationUseCase>()
    private val getCurrentPrayerUseCase by inject<GetCurrentPrayerUseCase>()

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun schedulePrayerNotifications(messageSender: MessageSender) {
        coroutineScope.launch {
            schedulePrayerNotificationsUseCase(messageSender)
        }
    }

    fun updateLocation(
        messageSender: MessageSender
    ) {
        coroutineScope.launch {
            updateLocationUseCase.invoke()
            schedulePrayerNotifications(messageSender)
            showNotification("Success", "Updated location has been done")
        }
    }

    suspend fun getCurrentPrayer(currentTime: String): String? {
        return withContext(Dispatchers.IO) {
            getCurrentPrayerUseCase(currentTime)
        }
    }

    override fun dispose() {
        coroutineScope.cancel()
    }
}