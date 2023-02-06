package dev.eduayuso.kmcs.android.features.posts.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
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
import dev.eduayuso.kmcs.domain.entities.PostEntity
import dev.eduayuso.kmcs.features.posts.list.IPostListViewModel
import dev.eduayuso.kmcs.features.posts.list.PostListContract
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PostListView(
    navController: NavHostController,
    viewModel: IPostListViewModel
) {

    val state: PostListContract.State by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is PostListContract.Effect.NavigateToDetail -> {
                    val route = "${AppConstants.RouteIds.postDetail}/${effect.id}"
                    navController.navigate(route)
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
        } else if (state.postList?.isEmpty() == true) {
            EmptyView(message = "Empty")
        } else if (state.postList != null) {
            PostListContent(viewModel, state.postList!!)
        }
    }
}

@ExperimentalCoilApi
@Composable
fun PostListContent(
    viewModel: IPostListViewModel,
    data: List<PostEntity>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        items(data) { post ->
            PostItemView(
                post = post
            ) {
                val event = PostListContract.Event.OnSelectPost(post.id)
                viewModel.setEvent(event)
            }

        }
    }
}

@Preview
@Composable
fun PostListPreview() {

    val initialState = PostListContract.State(
        isLoading = false,
        isError = false,
        postList = emptyList()
    )

    val viewModel = object: IPostListViewModel {
        override val state: StateFlow<PostListContract.State>
            get() = MutableStateFlow(initialState).asStateFlow()
        override val event: SharedFlow<PostListContract.Event>
            get() = MutableSharedFlow()
        override val effect: Flow<PostListContract.Effect>
            get() = Channel<PostListContract.Effect>().receiveAsFlow()

        override fun setEvent(event: PostListContract.Event) {
        }
    }

    PostListView(rememberNavController(), viewModel)
}