package com.kamel.rafiq_al_iman.domain.repository

import com.kamel.rafiq_al_iman.domain.entity.PrayerYearlyTiming

interface PrayerTimesRepository {
    suspend fun getPrayerTimesByYear(year: String): PrayerYearlyTiming
    suspend fun updatePrayerTimesByYear(year: String): PrayerYearlyTiming
}