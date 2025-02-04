package com.kamel.rafiq_al_iman.domain.usecase

import com.kamel.rafiq_al_iman.domain.repository.PrayerTimesRepository
import com.kamel.rafiq_al_iman.util.getCurrentDate

class UpdateLocationUseCase(
    private val prayerTimesRepository: PrayerTimesRepository
) {
    suspend operator fun invoke() {
        val year = getCurrentDate().year.toString()
        prayerTimesRepository.updatePrayerTimesByYear(year = year)
    }
}