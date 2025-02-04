package com.kamel.rafiq_al_iman.presentation.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.jcef.JBCefBrowser
import com.kamel.rafiq_al_iman.presentation.util.showNotification
import com.kamel.rafiq_al_iman.util.Res
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

class AudioPlayerToolWindow : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {

        val resourcePath = javaClass.classLoader.getResourceAsStream(Res.AUDIO_PLAYER_HTML)?.let {
            val tempFile = Files.createTempFile("audio_player", ".html")
            Files.copy(it, tempFile, StandardCopyOption.REPLACE_EXISTING)
            tempFile.toString()
        }

        if (resourcePath != null) {
            val tempFile = Files.createTempFile("audio_player", ".html")
            Files.write(tempFile, Files.readAllBytes(Paths.get(resourcePath)))
            try {
                val browser = JBCefBrowser()
                browser.loadURL(tempFile.toUri().toString()) // Load the HTML file from temp location
                val content = ContentFactory.getInstance().createContent(browser.component, "", false)
                toolWindow.contentManager.addContent(content)
            } catch (e: Exception) {
                showNotification("Error", e.message ?: "Unknown error occurred")
            }
        } else {
            throw Exception("Error: Unable to find audio_player.html in resources!")
        }
    }
}