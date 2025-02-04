package com.kamel.rafiq_al_iman.presentation.ui

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBTextArea
import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import com.kamel.rafiq_al_iman.util.Res
import java.awt.BorderLayout
import java.awt.ComponentOrientation
import java.awt.Dimension
import java.awt.Font
import javax.swing.*

class ZekrDialog(
    private val data: List<String>,
) : DialogWrapper(true) {

    private var currentIndex = 0
    private lateinit var textLabel: JBTextArea
    private lateinit var backButton: JButton
    private lateinit var nextButton: JButton

    init {
        init()
        title = "فَذَكِّرْ إِنْ نَفَعَتِ الذِّكْرَى"
    }

    override fun createCenterPanel(): JComponent {
        val panel = JPanel(GridLayoutManager(2, 1))

        textLabel = JBTextArea(data[currentIndex])
        textLabel.isEditable = false
        textLabel.lineWrap = true
        textLabel.wrapStyleWord = true
        textLabel.font = loadFontFromResources(Res.AMIRI_ARABIC_FONT_BOLD, 20f)
        textLabel.componentOrientation = ComponentOrientation.RIGHT_TO_LEFT
        textLabel.preferredSize = Dimension(400, 100)

        val navigationPanel = JPanel(BorderLayout())

        backButton = JButton("السابق").apply {
            isEnabled = false  // Initially disabled
            border = BorderFactory.createLineBorder(JBColor.WHITE, 1)
            font = loadFontFromResources(Res.AMIRI_ARABIC_FONT_BOLD, 16f)
            isContentAreaFilled = false
        }
        backButton.addActionListener {
            if (currentIndex > 0) {
                currentIndex--
                updateUI()
            }
        }

        nextButton = JButton("التالي").apply {
            border = BorderFactory.createLineBorder(JBColor.WHITE, 1)
            isContentAreaFilled = false
            font = loadFontFromResources(Res.AMIRI_ARABIC_FONT_BOLD, 16f)
        }

        nextButton.addActionListener {
            if (currentIndex < data.size - 1) {
                currentIndex++
                updateUI()
            }
        }

        navigationPanel.add(backButton, BorderLayout.EAST)
        navigationPanel.add(nextButton, BorderLayout.WEST)

        panel.add(
            textLabel,
            GridConstraints().apply {
                row = 0
                column = 0
                anchor = GridConstraints.ANCHOR_CENTER
            }
        )

        panel.add(
            navigationPanel,
            GridConstraints().apply {
                row = 1
                column = 0
                anchor = GridConstraints.ANCHOR_CENTER
            }
        )

        return panel
    }

    private fun loadFontFromResources(fontFileName: String, size: Float): Font {
        val fontStream = javaClass.classLoader.getResourceAsStream(fontFileName)
            ?: throw IllegalArgumentException("Font file not found: $fontFileName")
        return Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(size)
    }

    private fun updateUI() {
        textLabel.text = data[currentIndex]
        backButton.isEnabled = currentIndex > 0
        nextButton.isEnabled = currentIndex < data.size - 1
    }

    override fun createActions(): Array<Action> {
        return arrayOf(cancelAction)
    }

    override fun isResizable(): Boolean {
        return false
    }
}