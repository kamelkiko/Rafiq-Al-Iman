package com.kamel.rafiq_al_iman.data.util

import com.kamel.rafiq_al_iman.util.ErrorConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URI

suspend fun wrapApiResponse(urlString: String, method: String): String {
    val url = URI(urlString).toURL()
    val connection = withContext(Dispatchers.IO) {
        url.openConnection()
    } as HttpURLConnection

    try {
        connection.requestMethod = method
        connection.connectTimeout = 5000
        connection.readTimeout = 5000

        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return connection.inputStream.bufferedReader().use { it.readText() }
        } else {
            throw Exception("HTTP error code: $responseCode")
        }
    } catch (e: IOException) {
        throw Exception(ErrorConstant.NO_INTERNET_ERROR)
    } catch (e: Exception) {
        throw Exception(e.message.toString())
    } finally {
        connection.disconnect()
    }
}