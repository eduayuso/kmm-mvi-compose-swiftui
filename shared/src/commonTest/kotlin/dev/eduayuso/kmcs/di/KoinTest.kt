package dev.eduayuso.kmcs.di

import dev.eduayuso.kmcs.AppConstants
import dev.eduayuso.kmcs.data.HttpMock
import dev.eduayuso.kmcs.data.source.remote.ApiClient
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoinIntegrationTest(
    repositoryModule: Module
) =

    startKoin {
        modules(
            mockedApiModule,
            dataSourceModule,
            repositoryModule,
            dispatcherModule,
            platformModule()
        )
    }

fun initKoinUnitTest() =

    startKoin {
        modules(
            dispatcherModule,
            platformModule()
        )
    }

val mockedApiModule = module {

    single {
        HttpClient(engine = HttpMock.engine) {
            install(ContentNegotiation) {
                json()
            }
        }
    }
    single { ApiClient(get(), AppConstants.Apis.DummyApi.url) }
}