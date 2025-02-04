package com.kamel.rafiq_al_iman.domain.entity

data class PrayerYearlyTiming(
    val januaryTimes: List<PrayerTiming>,
    val octoberTimes: List<PrayerTiming>,
    val novemberTimes: List<PrayerTiming>,
    val decemberTimes: List<PrayerTiming>,
    val februaryTimes: List<PrayerTiming>,
    val marchTimes: List<PrayerTiming>,
    val aprilTimes: List<PrayerTiming>,
    val mayTimes: List<PrayerTiming>,
    val juneTimes: List<PrayerTiming>,
    val julyTimes: List<PrayerTiming>,
    val augustTimes: List<PrayerTiming>,
    val septemberTimes: List<PrayerTiming>
)