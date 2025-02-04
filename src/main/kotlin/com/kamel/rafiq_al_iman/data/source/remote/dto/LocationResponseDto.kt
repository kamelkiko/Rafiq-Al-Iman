package com.kamel.rafiq_al_iman.data.source.remote.dto

data class LocationResponseDto(
    val city: String? = null,
    val country_code: String? = null,
    val country_name: String? = null,
    val ip: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val metro_code: Int? = null,
    val region_code: String? = null,
    val region_name: String? = null,
    val timeZone: String? = null,
    val zip_code: String? = null
)