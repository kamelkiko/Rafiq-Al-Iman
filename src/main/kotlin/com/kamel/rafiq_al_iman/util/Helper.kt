package com.kamel.rafiq_al_iman.util

import com.kamel.rafiq_al_iman.domain.entity.PrayerTimeName
import java.time.LocalDate
import java.time.LocalTime

fun getCurrentTime(): LocalTime {
    return LocalTime.now()
}

fun getCurrentDate(): LocalDate {
    return LocalDate.now()
}

fun getPrayerNameByArabic(prayer: String): String {
    return when (prayer) {
        PrayerTimeName.FAJR.value -> "الفجر"
        PrayerTimeName.SUNRISE.value -> "الضحي"
        PrayerTimeName.DHUHR.value -> "الظهر"
        PrayerTimeName.ASR.value -> "العصر"
        PrayerTimeName.MAGHRIB.value -> "المغرب"
        PrayerTimeName.ISHA.value -> "العشاء"
        else -> throw IllegalArgumentException("Invalid prayer name: $prayer")
    }
}