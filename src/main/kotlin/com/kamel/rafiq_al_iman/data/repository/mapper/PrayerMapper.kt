package com.kamel.rafiq_al_iman.data.repository.mapper

import com.kamel.rafiq_al_iman.data.source.remote.dto.PrayerYearlyTimesResponseDto
import com.kamel.rafiq_al_iman.domain.entity.PrayerTiming
import com.kamel.rafiq_al_iman.domain.entity.PrayerYearlyTiming

fun PrayerYearlyTimesResponseDto.YearlyData.MonthTimes.toEntity() = PrayerTiming(
    dhuhr = timings?.Dhuhr ?: "",
    asr = timings?.Asr ?: "",
    fajr = timings?.Fajr ?: "",
    isha = timings?.Isha ?: "",
    maghrib = timings?.Maghrib ?: "",
    sunrise = timings?.Sunrise ?: "",
    sunset = timings?.Sunset ?: "",
    firstthird = timings?.Firstthird ?: "",
    lastthird = timings?.Lastthird ?: "",
    imsak = timings?.Imsak ?: "",
    midnight = timings?.Midnight ?: "",
)

fun PrayerYearlyTimesResponseDto.YearlyData.toEntity() = PrayerYearlyTiming(
    januaryTimes = `1`?.mapNotNull { it?.toEntity() } ?: emptyList(),
    februaryTimes = `2`?.mapNotNull { it?.toEntity() } ?: emptyList(),
    marchTimes = `3`?.mapNotNull { it?.toEntity() } ?: emptyList(),
    aprilTimes = `4`?.mapNotNull { it?.toEntity() } ?: emptyList(),
    mayTimes = `5`?.mapNotNull { it?.toEntity() } ?: emptyList(),
    juneTimes = `6`?.mapNotNull { it?.toEntity() } ?: emptyList(),
    julyTimes = `7`?.mapNotNull { it?.toEntity() } ?: emptyList(),
    augustTimes = `8`?.mapNotNull { it?.toEntity() } ?: emptyList(),
    septemberTimes = `9`?.mapNotNull { it?.toEntity() } ?: emptyList(),
    octoberTimes = `10`?.mapNotNull { it?.toEntity() } ?: emptyList(),
    novemberTimes = `11`?.mapNotNull { it?.toEntity() } ?: emptyList(),
    decemberTimes = `12`?.mapNotNull { it?.toEntity() } ?: emptyList(),
)