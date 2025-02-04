package com.kamel.rafiq_al_iman.di.module

import com.kamel.rafiq_al_iman.data.repository.PrayerTimesRepositoryImpl
import com.kamel.rafiq_al_iman.di.koin.module
import com.kamel.rafiq_al_iman.domain.repository.PrayerTimesRepository

val repositoryModule: module = {
    single { PrayerTimesRepositoryImpl(get(), get()) } bind PrayerTimesRepository::class
}