package dev.eduayuso.kmcs.di

import dev.eduayuso.kmcs.AppConstants
import dev.eduayuso.kmcs.data.LocalProperties
import dev.eduayuso.kmcs.data.repository.PostsRepository
import dev.eduayuso.kmcs.data.repository.UsersRepository
import dev.eduayuso.kmcs.data.source.remote.ApiClient
import dev.eduayuso.kmcs.data.source.remote.PostsRemoteDataSource
import dev.eduayuso.kmcs.data.source.remote.UsersRemoteDataSource
import dev.eduayuso.kmcs.domain.interactors.GetPostCommentsUseCase
import dev.eduayuso.kmcs.domain.interactors.GetPostDetailUseCase
import dev.eduayuso.kmcs.domain.interactors.GetPostListUseCase
import dev.eduayuso.kmcs.domain.interactors.GetUserListUseCase
import dev.eduayuso.kmcs.domain.interactors.impl.GetPostCommentsInteractor
import dev.eduayuso.kmcs.domain.interactors.impl.GetPostDetailInteractor
import dev.eduayuso.kmcs.domain.interactors.impl.GetPostListInteractor
import dev.eduayuso.kmcs.domain.interactors.impl.GetUserListInteractor
import dev.eduayuso.kmcs.domain.repository.IPostsRepository
import dev.eduayuso.kmcs.domain.repository.IUsersRepository
import dev.eduayuso.kmcs.features.posts.detail.PostDetailViewModel
import dev.eduayuso.kmcs.features.posts.list.PostListViewModel
import dev.eduayuso.kmcs.features.users.list.UserListViewModel
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =

    startKoin {
        appDeclaration()
        modules(
            apiModule,
            dataSourceModule,
            repositoryModule,
            useCasesModule,
            dispatcherModule,
            viewModelModule,
            platformModule()
        )
    }

fun initKoinFromIOS(localProperties: LocalProperties) = initKoin {
    modules(
        module {
            single { localProperties }
        }
    )
}

val apiModule = module {

    single {
        Json {
            encodeDefaults = false
            isLenient = true
            ignoreUnknownKeys = true
            useArrayPolymorphism = true
        }
    }

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(get())
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        co.touchlab.kermit.Logger.v { "Ktor $message" }
                    }
                }
                level = LogLevel.ALL
            }
        }
    }
    single { ApiClient(get(), AppConstants.Apis.DummyApi.url, get()) }
}

val dataSourceModule = module {

    single { PostsRemoteDataSource(get()) }
    single { UsersRemoteDataSource(get()) }
}

val repositoryModule = module {

    single<IPostsRepository> { PostsRepository(get()) }
    single<IUsersRepository> { UsersRepository(get()) }
}

val useCasesModule = module {

    single<GetPostListUseCase> { GetPostListInteractor(get()) }
    single<GetUserListUseCase> { GetUserListInteractor(get()) }
    single<GetPostDetailUseCase> { GetPostDetailInteractor(get()) }
    single<GetPostCommentsUseCase> { GetPostCommentsInteractor(get()) }
}

val viewModelModule = module {

    single { PostListViewModel(get()) }
    single { UserListViewModel(get()) }
    single { PostDetailViewModel(get(), get()) }
}

val dispatcherModule = module {

    factory { Dispatchers.Default }
}