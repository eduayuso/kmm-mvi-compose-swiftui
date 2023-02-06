package dev.eduayuso.kmcs

import dev.eduayuso.kmcs.di.initKoinIntegrationTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

@ExperimentalCoroutinesApi
abstract class IntegrationTestCase: KoinTest {

    @BeforeTest
    fun beforeTest() {

        Dispatchers.setMain(UnconfinedTestDispatcher())
        initKoinIntegrationTest(repositoryModule)
    }

    abstract val repositoryModule: Module

    @AfterTest
    fun afterTest() {

        stopKoin()
    }
}