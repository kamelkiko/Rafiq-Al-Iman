package com.kamel.rafiq_al_iman.data.util.localizedMessages.languages

data class ArabicLocalizedMessages(
    override val beforeAzanTimeCome: String = "قد اقترب موعد آذان صلاة",
    override val prayerReminder: String = "التذكير بالصلاة",
    override val didYouPray: String = "هل صليت",
    override val timeToPray: String = "حان وقت صلاة",
    override val pray: String = "صلاة"
) : LocalizedMessages