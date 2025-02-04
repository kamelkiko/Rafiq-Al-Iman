package com.kamel.rafiq_al_iman.di.module

import com.kamel.rafiq_al_iman.data.util.localizedMessages.LocalMessageFactory
import com.kamel.rafiq_al_iman.di.koin.module
import com.kamel.rafiq_al_iman.domain.util.MessageSender
import com.kamel.rafiq_al_iman.domain.util.MessageSenderImpl

val localMessageModule: module = {
    single { LocalMessageFactory() }
    single { MessageSenderImpl() } bind MessageSender::class
}