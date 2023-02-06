package dev.eduayuso.kmcs.data.repository

import dev.eduayuso.kmcs.IntegrationTestCase
import dev.eduayuso.kmcs.domain.repository.IUsersRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.koin.core.component.inject
import org.koin.dsl.module
import kotlin.test.Test
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class UsersRepositoryTest: IntegrationTestCase() {

    private val repository: IUsersRepository by inject()

    override val repositoryModule = module {

        single<IUsersRepository> { UsersRepository(get()) }
    }

    @Test
    fun `when call repository to get all users expect return a list of users`() = runTest {

        val result = repository.getAll()
        assertTrue(result.isNotEmpty())
        assertTrue(result.first().firstName == "First")
    }
}