package com.kamel.rafiq_al_iman.di.koin

import kotlin.reflect.KClass

class Koin {
    val instances = mutableMapOf<String, Any>()
    val definitions = mutableMapOf<String, () -> Any>()
    val singletonDefinitions = mutableSetOf<String>()
    val bindings = mutableMapOf<String, String>()

    inline fun <reified T : Any> single(noinline provider: () -> T): KoinDefinition {
        val key = T::class.java.name
        return register(key, provider, true)
    }

    inline fun <reified T : Any> factory(noinline provider: () -> T): KoinDefinition {
        val key = T::class.java.name
        return register(key, provider, false)
    }

    fun <T : Any> register(key: String, provider: () -> T, isSingleton: Boolean): KoinDefinition {
        definitions[key] = provider
        if (isSingleton) singletonDefinitions.add(key)
        return KoinDefinition(this, key)
    }

    fun bind(clazz: KClass<*>, implementationKey: String) {
        bindings[clazz.java.name] = implementationKey
    }

    inline fun <reified T : Any> get(): T {
        val requestedName = T::class.java.name
        val resolvedKey = bindings[requestedName] ?: requestedName

        return if (singletonDefinitions.contains(resolvedKey)) {
            instances.getOrPut(resolvedKey) { definitions[resolvedKey]!!() } as T
        } else {
            definitions[resolvedKey]!!() as T
        }
    }
}

class KoinDefinition(private val koin: Koin, private val key: String) {
    infix fun bind(clazz: KClass<*>) {
        koin.bind(clazz, key)
    }
}

object GlobalKoin {
    private var instance: Koin? = null

    fun start(koin: Koin) {
        instance = koin
    }

    fun get(): Koin = instance ?: throw IllegalStateException("Koin not initialized")
}

interface KoinComponent {
    private val koin: Koin get() = GlobalKoin.get()
}

inline fun <reified T : Any> inject(): Lazy<T> = lazy { GlobalKoin.get().get<T>() }

typealias module = Koin.() -> Unit

fun modules(vararg moduleBlocks: module): module = {
    moduleBlocks.forEach { block -> block() }
}

fun startKoin(vararg moduleBlocks: module) {
    val rootKoin = Koin()
    moduleBlocks.forEach { block -> rootKoin.block() }
    GlobalKoin.start(rootKoin)
}