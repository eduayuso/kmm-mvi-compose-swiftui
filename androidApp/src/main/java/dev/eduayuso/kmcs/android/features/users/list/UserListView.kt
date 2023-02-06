package dev.eduayuso.kmcs.android.features.users.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import dev.eduayuso.kmcs.AppConstants
import dev.eduayuso.kmcs.android.components.EmptyView
import dev.eduayuso.kmcs.android.components.ErrorView
import dev.eduayuso.kmcs.android.components.LoadingView
import dev.eduayuso.kmcs.android.features.posts.list.PostItemView
import dev.eduayuso.kmcs.domain.entities.PostEntity
import dev.eduayuso.kmcs.domain.entities.UserEntity
import dev.eduayuso.kmcs.features.posts.list.IPostListViewModel
import dev.eduayuso.kmcs.features.posts.list.PostListContract
import dev.eduayuso.kmcs.features.users.list.IUserListViewModel
import dev.eduayuso.kmcs.features.users.list.UserListContract
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoilApi::class)
@Composable
fun UserListView(
    navController: NavHostController,
    viewModel: IUserListViewModel
) {

    val state: UserListContract.State by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is UserListContract.Effect.NavigateToDetail -> {
                    // TODO
                }
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
    ) {
        if (state.isLoading) {
            LoadingView()
        } else if (state.isError) {
            ErrorView(message = "Error fetching data")
        } else if (state.userList?.isEmpty() == true) {
            EmptyView(message = "Empty")
        } else if (state.userList != null) {
            UserListContent(viewModel, state.userList!!)
        }
    }
}

@ExperimentalCoilApi
@Composable
fun UserListContent(
    viewModel: IUserListViewModel,
    data: List<UserEntity>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        items(data) { user ->
            UserItemView(user) {
                val event = UserListContract.Event.OnSelectUser(user.id)
                viewModel.setEvent(event)
            }

        }
    }
}

@Preview
@Composable
fun UserListPreview() {

    val initialState = UserListContract.State(
        isLoading = false,
        isError = false,
        userList = emptyList()
    )

    val viewModel = object: IUserListViewModel {
        override val state: StateFlow<UserListContract.State>
            get() = MutableStateFlow(initialState).asStateFlow()
        override val event: SharedFlow<UserListContract.Event>
            get() = MutableSharedFlow()
        override val effect: Flow<UserListContract.Effect>
            get() = Channel<UserListContract.Effect>().receiveAsFlow()

        override fun setEvent(event: UserListContract.Event) {
        }
    }

    UserListView(rememberNavController(), viewModel)
}