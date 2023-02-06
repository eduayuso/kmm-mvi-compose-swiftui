package dev.eduayuso.kmcs.data.repository

import dev.eduayuso.kmcs.IntegrationTestCase
import dev.eduayuso.kmcs.domain.repository.IPostsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.koin.core.component.inject
import org.koin.dsl.module
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class PostsRepositoryTest: IntegrationTestCase() {

    private val repository: IPostsRepository by inject()

    override val repositoryModule = module {

        single<IPostsRepository> { PostsRepository(get()) }
    }

    @Test
    fun `when calling repository to get all posts expect return a list of posts`() = runTest {

        val result = repository.getAll()
        assertTrue(result.isNotEmpty())
        assertTrue(result.first().text == "Hola")
    }

    @Test
    fun `when calling repository to get a post detail expect return a post entity`() = runTest {

        val result = repository.getById(id = "1")
        assertNotNull(result)
        assertTrue(result.text == "Hola")
    }
}