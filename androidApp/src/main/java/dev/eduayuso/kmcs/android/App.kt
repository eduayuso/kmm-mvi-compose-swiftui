package dev.eduayuso.kmcs.android

import android.app.Application
import dev.eduayuso.kmcs.android.system.AndroidLocalProperties
import dev.eduayuso.kmcs.data.LocalProperties
import dev.eduayuso.kmcs.di.KoinViewModels
import dev.eduayuso.kmcs.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.dsl.module

class App: Application(), KoinComponent {

    override fun onCreate() {

        super.onCreate()
        initKoin {
            modules(
                module {
                    single { KoinViewModels() }
                    single<LocalProperties> { AndroidLocalProperties() }
                }
            )
        }
    }
}