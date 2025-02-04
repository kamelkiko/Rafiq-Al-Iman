package com.kamel.rafiq_al_iman.domain.usecase

import com.kamel.rafiq_al_iman.domain.entity.PrayerYearlyTiming
import com.kamel.rafiq_al_iman.domain.repository.PrayerTimesRepository

class GetPrayerTimesUseCase(
    private val prayerTimesRepository: PrayerTimesRepository
) {
    suspend operator fun invoke(year: String): PrayerYearlyTiming {
        return prayerTimesRepository.getPrayerTimesByYear(year)
    }
}