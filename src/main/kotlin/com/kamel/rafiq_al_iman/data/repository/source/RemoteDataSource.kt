package com.kamel.rafiq_al_iman.data.repository.source

import com.kamel.rafiq_al_iman.data.source.remote.dto.PrayerYearlyTimesResponseDto

interface RemoteDataSource {
       suspend fun getPrayerTimesByYear(year: String): PrayerYearlyTimesResponseDto.YearlyData
}