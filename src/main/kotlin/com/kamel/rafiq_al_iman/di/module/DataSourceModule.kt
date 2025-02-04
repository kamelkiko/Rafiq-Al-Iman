package com.kamel.rafiq_al_iman.di.module

import com.kamel.rafiq_al_iman.data.repository.source.LocalDataSource
import com.kamel.rafiq_al_iman.data.repository.source.RemoteDataSource
import com.kamel.rafiq_al_iman.data.source.local.JsonFileLocalDataSourceImpl
import com.kamel.rafiq_al_iman.data.source.remote.NativeRemoteDataSourceImpl
import com.kamel.rafiq_al_iman.di.koin.module

val dataSourceModule: module = {
    single { NativeRemoteDataSourceImpl() } bind RemoteDataSource::class
    single { JsonFileLocalDataSourceImpl() } bind LocalDataSource::class
}