package dev.eduayuso.kmcs

import dev.eduayuso.kmcs.di.initKoinUnitTest
import dev.eduayuso.kmcs.domain.interactors.type.Resource
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.koin.core.context.stopKoin
import kotlin.reflect.KClass
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.fail

@ExperimentalCoroutinesApi
open class UnitTestCase {

    @BeforeTest
    open fun beforeTest() {

        Dispatchers.setMain(UnconfinedTestDispatcher())
        initKoinUnitTest()
        MockKAnnotations.init(this)
    }

    @AfterTest
    open fun afterTest() {

        stopKoin()
        clearAllMocks()
    }

    fun assertSuccess(result: Resource<*>) {

        val actual: KClass<*> = result::class
        assertEquals(Resource.Success::class, actual, "The result is not Success => ")
    }

    fun assertError(result: Resource<*>) {

        val actual: KClass<*> = result::class
        assertEquals(Resource.Error::class, actual, "The result is not Failure => ")
    }

    fun resourceTypeFails() {

        fail(message = "Resource type is incorrect")
    }
}