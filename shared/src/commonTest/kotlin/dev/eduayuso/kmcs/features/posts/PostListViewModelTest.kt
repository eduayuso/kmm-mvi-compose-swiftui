package dev.eduayuso.kmcs.features.posts

import app.cash.turbine.test
import dev.eduayuso.kmcs.AppConstants.RouteIds.userList
import dev.eduayuso.kmcs.UnitTestCase
import dev.eduayuso.kmcs.data.MockedResponses
import dev.eduayuso.kmcs.domain.interactors.GetUserListUseCase
import dev.eduayuso.kmcs.domain.interactors.type.Resource
import dev.eduayuso.kmcs.features.users.list.UserListContract
import dev.eduayuso.kmcs.features.users.list.UserListViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class PostListViewModelTest: UnitTestCase() {

    @MockK
    lateinit var useCase: GetUserListUseCase

    @InjectMockKs
    lateinit var viewModel: UserListViewModel

    @Test
    fun `when get users successfully expect receive a list of users`() = runTest {

        val userList = listOf(MockedResponses.userEntity)

        coEvery { useCase.invoke() } returns flow {
            emit(Resource.Success(userList))
        }

        viewModel.state.test {

            viewModel.createInitialState()
            skipItems(1)

            viewModel.handleEvent(UserListContract.Event.OnGetUserList)

            assertTrue(awaitItem().isLoading)
            val resultItem = awaitItem()
            assertFalse(resultItem.userList.isNullOrEmpty())
            assertFalse(resultItem.isLoading)
        }
    }

    @Test
    fun `when get users not successfully expect not receive an error`() = runTest {

        coEvery { useCase.invoke() } returns flow {
            emit(Resource.Error(Exception("Failure")))
        }

        viewModel.state.test {

            viewModel.createInitialState()
            skipItems(1)

            viewModel.handleEvent(UserListContract.Event.OnGetUserList)

            assertTrue(awaitItem().isLoading)
            val resultItem = awaitItem()
            assertTrue(resultItem.userList.isNullOrEmpty())
            assertTrue(resultItem.isError)
            assertFalse(resultItem.isLoading)
        }
    }
}