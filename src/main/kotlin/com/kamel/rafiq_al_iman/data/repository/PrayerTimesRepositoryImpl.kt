package com.kamel.rafiq_al_iman.data.repository

import com.intellij.notification.NotificationType
import com.kamel.rafiq_al_iman.data.repository.mapper.toEntity
import com.kamel.rafiq_al_iman.data.repository.source.LocalDataSource
import com.kamel.rafiq_al_iman.data.repository.source.RemoteDataSource
import com.kamel.rafiq_al_iman.domain.entity.PrayerYearlyTiming
import com.kamel.rafiq_al_iman.domain.repository.PrayerTimesRepository
import com.kamel.rafiq_al_iman.presentation.util.showNotification
import com.kamel.rafiq_al_iman.util.ErrorConstant

class PrayerTimesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : PrayerTimesRepository {

    override suspend fun getPrayerTimesByYear(year: String): PrayerYearlyTiming {
        val requestedYear = year.toInt()
        return try {
            val storedYear = localDataSource.getStoredYear()
            if (storedYear != null && storedYear == requestedYear) {
                // Try to retrieve cached data if available.
                val cachedData = localDataSource.getYearlyData(requestedYear)
                if (cachedData != null) {
                    return cachedData.toEntity()
                }
            }
            val remoteData = remoteDataSource.getPrayerTimesByYear(year)
            localDataSource.deleteAllYearlyData()
            localDataSource.saveYearlyData(yearlyData = remoteData, year = requestedYear)
            localDataSource.updateStoredYear(requestedYear)
            remoteData.toEntity()
        } catch (e: Exception) {
            showNotification(ErrorConstant.PLUGIN_ERROR, e.message.toString(), NotificationType.ERROR)
            throw e
        }
    }

    override suspend fun updatePrayerTimesByYear(year: String): PrayerYearlyTiming {
        val requestedYear = year.toInt()
        return try {
            localDataSource.deleteAllYearlyData()
            val remoteData = remoteDataSource.getPrayerTimesByYear(year)
            localDataSource.saveYearlyData(yearlyData = remoteData, year = requestedYear)
            localDataSource.updateStoredYear(requestedYear)
            remoteData.toEntity()
        } catch (e: Exception) {
            showNotification(ErrorConstant.PLUGIN_ERROR, e.message.toString(), NotificationType.ERROR)
            throw e
        }
    }
}