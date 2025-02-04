package com.kamel.rafiq_al_iman.presentation.service

import com.intellij.openapi.components.Service
import com.kamel.rafiq_al_iman.presentation.util.showNotification
import com.kamel.rafiq_al_iman.presentation.util.showZekrDialog
import com.kamel.rafiq_al_iman.util.hamd
import com.kamel.rafiq_al_iman.util.istkhfar
import com.kamel.rafiq_al_iman.util.tasbeh
import com.kamel.rafiq_al_iman.util.zekr

@Service(Service.Level.PROJECT)
class ZekrService {

    fun startShowingRandomZekr() {
        val randomAyah = tasbeh.shuffled().random()
        showNotification(
            title = "متنساش تسمي الله و إن شاء الله يشتغل من اول مره",
            message = randomAyah
        )
    }

    fun startShowingRandomIstkhfar() {
        val randomIstkhar = istkhfar.shuffled().random()
        showNotification(
            title = "حاول تاني و اقرأ المشكله كويس و حاول تحلها ربنا معاك",
            message = randomIstkhar
        )
    }

    fun startShowingRandomHamd() {
        val randomHamd = hamd.shuffled().random()
        showNotification(
            title = "تمت بنجاح بفضل الله عاش يا هندسه",
            message = randomHamd
        )
    }

    fun startShowingRandomTasbeh() {
        showZekrDialog(zekr.shuffled())
    }

}