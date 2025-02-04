package com.kamel.rafiq_al_iman.data.repository.source

import com.kamel.rafiq_al_iman.data.source.remote.dto.PrayerYearlyTimesResponseDto

interface LocalDataSource {
    suspend fun deleteAllYearlyData()
    suspend fun saveYearlyData(yearlyData: PrayerYearlyTimesResponseDto.YearlyData, year: Int)
    suspend fun getYearlyData(year: Int): PrayerYearlyTimesResponseDto.YearlyData?
    suspend fun getStoredYear(): Int?
    suspend fun updateStoredYear(year: Int)
}