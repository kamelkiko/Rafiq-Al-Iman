package com.kamel.rafiq_al_iman.domain.entity

data class PrayerTiming(
    val asr: String,
    val dhuhr: String,
    val fajr: String,
    val firstthird: String,
    val imsak: String,
    val isha: String,
    val lastthird: String,
    val maghrib: String,
    val midnight: String,
    val sunrise: String,
    val sunset: String
)