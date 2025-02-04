package com.kamel.rafiq_al_iman.data.source.remote

import com.google.gson.Gson
import com.kamel.rafiq_al_iman.data.repository.source.RemoteDataSource
import com.kamel.rafiq_al_iman.data.source.remote.dto.LocationResponseDto
import com.kamel.rafiq_al_iman.data.source.remote.dto.PrayerYearlyTimesResponseDto
import com.kamel.rafiq_al_iman.data.util.wrapApiResponse
import com.kamel.rafiq_al_iman.util.ApiConstant
import com.kamel.rafiq_al_iman.util.ErrorConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URLEncoder

class NativeRemoteDataSourceImpl : RemoteDataSource {
    private val gson by lazy { Gson() }

    private suspend fun getCurrentLocation(): LocationResponseDto {
        val jsonResponse = wrapApiResponse(ApiConstant.LOCATION_URL_ENDPOINT, "GET")
        return gson.fromJson(jsonResponse, LocationResponseDto::class.java)
    }

    override suspend fun getPrayerTimesByYear(year: String): PrayerYearlyTimesResponseDto.YearlyData {
        val location = getCurrentLocation()
        val method = if (location.country_code == EGYPT_COUNTRY_CODE) EGYPT_TIME_METHOD else WORLD_TIME_METHOD

        // Encode URL parameters
        val encodedLat = withContext(Dispatchers.IO) {
            URLEncoder.encode(location.latitude.toString().take(VALID_SIZE), ENCODED_UTF_8)
        }
        val encodedLong = withContext(Dispatchers.IO) {
            URLEncoder.encode(location.longitude.toString().take(VALID_SIZE), ENCODED_UTF_8)
        }

        val url =
            "${ApiConstant.PRAYER_TIMES_YEARLY_URL_ENDPOINT}/$year?$LAT=$encodedLat&$LNG=$encodedLong&$METHOD=$method"
        val jsonResponse = wrapApiResponse(url, "GET")

        return gson.fromJson(jsonResponse, PrayerYearlyTimesResponseDto::class.java)?.data
            ?: throw Exception(ErrorConstant.SOMETHING_HAPPEN_ERROR)
    }

    private companion object {
        private const val EGYPT_TIME_METHOD = 5
        private const val WORLD_TIME_METHOD = 3
        private const val EGYPT_COUNTRY_CODE = "EG"
        private const val VALID_SIZE = 10
        private const val LAT = "latitude"
        private const val LNG = "longitude"
        private const val METHOD = "method"
        private const val ENCODED_UTF_8 = "UTF-8"
    }
}