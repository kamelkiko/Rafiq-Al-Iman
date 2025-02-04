package com.kamel.rafiq_al_iman.data.util.localizedMessages

import com.kamel.rafiq_al_iman.data.util.localizedMessages.languages.ArabicLocalizedMessages
import com.kamel.rafiq_al_iman.data.util.localizedMessages.languages.EnglishLocalizedMessages
import com.kamel.rafiq_al_iman.data.util.localizedMessages.languages.LocalizedMessages
import com.kamel.rafiq_al_iman.util.Constant

class LocalMessageFactory {
    fun createLocalizedMessages(languageCode: String): LocalizedMessages {
        return map[languageCode.uppercase()] ?: EnglishLocalizedMessages()
    }
}

private val map = mapOf(
    Language.ENGLISH.code to EnglishLocalizedMessages(),
    Language.ARABIC.code to ArabicLocalizedMessages(),
)

enum class Language(val code: String) {
    ENGLISH(Constant.ENGLISH_CODE),
    ARABIC(Constant.ARABIC_CODE),
}