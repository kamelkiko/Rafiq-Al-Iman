package com.kamel.rafiq_al_iman.di.module

import com.kamel.rafiq_al_iman.di.koin.module
import com.kamel.rafiq_al_iman.di.koin.modules

val appModule: module = modules(
    dataSourceModule,
    repositoryModule,
    useCaseModule,
    localMessageModule
)