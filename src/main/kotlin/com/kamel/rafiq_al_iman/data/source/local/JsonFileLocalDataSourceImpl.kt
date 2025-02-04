package com.kamel.rafiq_al_iman.data.source.local

import com.google.gson.Gson
import com.kamel.rafiq_al_iman.data.repository.source.LocalDataSource
import com.kamel.rafiq_al_iman.data.source.remote.dto.PrayerYearlyTimesResponseDto
import com.kamel.rafiq_al_iman.util.AppPaths
import com.kamel.rafiq_al_iman.util.Res
import java.io.File

class JsonFileLocalDataSourceImpl : LocalDataSource {

    private val gson by lazy { Gson() }
    private val dbFile = File(DB_PATH)
    private val yearFile = File(YEAR_DB_PATH)

    override suspend fun deleteAllYearlyData() {
        if (isDbExist()) dbFile.delete()
        if (isDbYearExist()) yearFile.delete()
    }

    override suspend fun saveYearlyData(yearlyData: PrayerYearlyTimesResponseDto.YearlyData, year: Int) {
        saveJsonToFile(gson.toJson(yearlyData))
    }

    override suspend fun getYearlyData(year: Int): PrayerYearlyTimesResponseDto.YearlyData? {
        return if (isDbExist()) {
            val json = readJsonFromFile()
            return parseJson(json)
        } else null
    }

    override suspend fun getStoredYear(): Int? {
        return if (isDbYearExist()) {
            val text = yearFile.readText().trim()
            text.toIntOrNull()
        } else null
    }

    override suspend fun updateStoredYear(year: Int) {
        yearFile.writeText(year.toString())
    }

    private fun isDbExist(): Boolean {
        return File(DB_PATH).exists()
    }

    private fun isDbYearExist(): Boolean {
        return File(YEAR_DB_PATH).exists()
    }

    private fun parseJson(json: String): PrayerYearlyTimesResponseDto.YearlyData? {
        return gson.fromJson(json, PrayerYearlyTimesResponseDto.YearlyData::class.java)
    }

    private fun saveJsonToFile(json: String) {
        dbFile.writeText(json)
    }

    private fun readJsonFromFile(): String {
        return dbFile.readText()
    }

    private companion object {
        private val DB_PATH = AppPaths.getDatabasePath() + Res.DB_FILE
        private val YEAR_DB_PATH = AppPaths.getDatabasePath() + Res.YEAR_FILE
    }
}