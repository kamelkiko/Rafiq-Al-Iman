package com.kamel.rafiq_al_iman.util

import java.io.File
import java.nio.file.Paths

object AppPaths {
    private const val APP_NAME = "Rafiq Al-Iman"

    // Get OS-specific data directory
    private fun getDataDirectory(): String {
        return when {
            System.getProperty("os.name").startsWith("Win", ignoreCase = true) -> {
                Paths.get(System.getenv("LOCALAPPDATA"), APP_NAME).toString()
            }

            System.getProperty("os.name").startsWith("Mac", ignoreCase = true) -> {
                Paths.get(System.getProperty("user.home"), "Library", "Application Support", APP_NAME).toString()
            }

            else -> { // Linux/Unix
                Paths.get(System.getProperty("user.home"), ".local", "share", APP_NAME).toString()
            }
        }
    }

    // Get full DB path
    fun getDatabasePath(): String {
        val dataDir = File(getDataDirectory())
        if (!dataDir.exists()) dataDir.mkdirs() // Create directory if missing

        return Paths.get(dataDir.absolutePath).toString()
    }
}