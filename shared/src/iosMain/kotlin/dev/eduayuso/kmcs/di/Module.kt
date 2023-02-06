package dev.eduayuso.kmcs.di

import dev.eduayuso.kmcs.executor.MainDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {

    single { MainDispatcher() }
}
