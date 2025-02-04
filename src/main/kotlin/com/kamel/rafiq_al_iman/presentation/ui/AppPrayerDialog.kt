package com.kamel.rafiq_al_iman.presentation.ui

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextArea
import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import com.intellij.util.ui.JBUI
import com.kamel.rafiq_al_iman.util.Res
import java.awt.ComponentOrientation
import java.awt.Dimension
import java.awt.Font
import javax.swing.Action
import javax.swing.JComponent
import javax.swing.JPanel

class AppPrayerDialog(
    private val message: String,
    private val data: String,
    mTitle: String,
) : DialogWrapper(true) {

    private lateinit var ayatTextArea: JBTextArea
    private lateinit var messageLabel: JBLabel

    init {
        init()
        title = mTitle
    }

    override fun createCenterPanel(): JComponent {
        val panel = JPanel(GridLayoutManager(2, 1))

        // Add the prayer message
        messageLabel = JBLabel(message)
        messageLabel.border = JBUI.Borders.emptyBottom(16)
        messageLabel.font = loadFontFromResources(Res.AMIRI_ARABIC_FONT_BOLD, 18f)
        panel.add(
            messageLabel,
            GridConstraints().apply {
                row = 0
                column = 0
                anchor = GridConstraints.ANCHOR_CENTER
            }
        )


        ayatTextArea = JBTextArea(data)
        ayatTextArea.isEditable = false
        ayatTextArea.lineWrap = true
        ayatTextArea.wrapStyleWord = true
        ayatTextArea.font = loadFontFromResources(Res.AMIRI_ARABIC_FONT_BOLD, 20f)
        ayatTextArea.componentOrientation = ComponentOrientation.RIGHT_TO_LEFT
        ayatTextArea.preferredSize = Dimension(400, 100)

        panel.add(
            ayatTextArea,
            GridConstraints().apply {
                row = 1
                column = 0
                anchor = GridConstraints.ANCHOR_CENTER
            }
        )

        return panel
    }

    override fun createActions(): Array<Action> {
        return arrayOf(okAction)
    }

    private fun loadFontFromResources(fontFileName: String, size: Float): Font {
        val fontStream = javaClass.classLoader.getResourceAsStream(fontFileName)
            ?: throw IllegalArgumentException("Font file not found: $fontFileName")
        return Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(size)
    }

    override fun isResizable(): Boolean {
        return false
    }
}