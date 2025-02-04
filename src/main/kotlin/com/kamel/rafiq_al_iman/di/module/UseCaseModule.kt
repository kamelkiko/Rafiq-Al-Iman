package com.kamel.rafiq_al_iman.di.module

import com.kamel.rafiq_al_iman.di.koin.module
import com.kamel.rafiq_al_iman.domain.usecase.GetCurrentPrayerUseCase
import com.kamel.rafiq_al_iman.domain.usecase.GetPrayerTimesUseCase
import com.kamel.rafiq_al_iman.domain.usecase.SchedulePrayerNotificationsUseCase
import com.kamel.rafiq_al_iman.domain.usecase.UpdateLocationUseCase
import com.kamel.rafiq_al_iman.domain.usecase.util.PrayerTimesHelper

val useCaseModule: module = {
    factory { UpdateLocationUseCase(get()) }
    factory { GetPrayerTimesUseCase(get()) }
    factory { SchedulePrayerNotificationsUseCase(get(), get()) }
    factory { GetCurrentPrayerUseCase(get(), get()) }
    factory { PrayerTimesHelper() }
}