package dev.eduayuso.kmcs.android.features.posts.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import dev.eduayuso.kmcs.android.components.EmptyView
import dev.eduayuso.kmcs.android.components.ErrorView
import dev.eduayuso.kmcs.android.components.LoadingView
import dev.eduayuso.kmcs.android.components.TopBarView
import dev.eduayuso.kmcs.domain.entities.CommentEntity
import dev.eduayuso.kmcs.domain.entities.PostEntity
import dev.eduayuso.kmcs.features.posts.detail.IPostDetailViewModel
import dev.eduayuso.kmcs.features.posts.detail.PostDetailContract
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@ExperimentalCoilApi
@Composable
fun PostDetailView(
    navController: NavHostController,
    viewModel: IPostDetailViewModel
) {

    val state: PostDetailContract.State by viewModel.state.collectAsState()

    Scaffold(
        scaffoldState = rememberScaffoldState(),
        topBar = {
            TopBarView(navController, title = state.post?.text?.uppercase() ?: "")
        },
    ) { padding ->

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(padding)
        ) {
            if (state.isLoadingDetail) {
                LoadingView()
            } else if (state.isError) {
                ErrorView(message = "Error fetching detail")
            } else if (state.post == null) {
                EmptyView(message = "Empty")
            } else {
                PostDetailContentView(state)
            }
        }
    }
}

@Composable
fun PostDetailContentView(state: PostDetailContract.State) {

    Column(
        modifier = Modifier
            .padding(all = 16.dp)) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberImagePainter(state.post?.owner?.picture),
                contentDescription = null,
                modifier = Modifier
                    .padding(all = 16.dp)
                    .clip(CircleShape)
                    .width(56.dp)
                    .height(56.dp)
            )
            Column {
                Text(
                    text = "${state.post?.owner?.firstName ?: ""} ${state.post?.owner?.lastName ?: ""}",
                    color = Color.Gray,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = state.post?.publishDate ?: "",
                    color = Color.Gray
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier
                    .padding(all = 16.dp)) {

                Row {
                    Icon(
                        imageVector = Icons.Filled.ThumbUp,
                        contentDescription = "Back"
                    )
                    Text(
                        text = "${state.post?.likes ?: 0}",
                        color = Color.Gray
                    )
                }
            }
        }

        Image(
            painter = rememberImagePainter(state.post?.image),
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(256.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(4.dp))
        )

        Text(
            text = state.post?.text ?: "",
            color = Color.Gray,
            modifier = Modifier
                .padding(all = 8.dp)
        )

        if (!state.isLoadingComments) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                items(state.comments ?: emptyList()) { comment ->

                    CommentView(comment)
                }
            }
        }
    }
}

@Composable
fun CommentView(comment: CommentEntity) {

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(comment.owner?.picture),
                contentDescription = null,
                modifier = Modifier
                    .padding(all = 14.dp)
                    .clip(CircleShape)
                    .width(36.dp)
                    .height(36.dp)
            )
            Column {
                Text(
                    text = comment.publishDate ?: "",
                    color = Color.Gray
                )
                Text(
                    text = comment.message ?: "",
                    color = Color.Gray,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun PostDetailPreview() {

    val initialState = PostDetailContract.State(
        post = PostEntity(id = "1", text = "hola"),
        isLoadingComments = false,
        isError = false,
        comments = null
    )

    val viewModel = object: IPostDetailViewModel {
        override val state: StateFlow<PostDetailContract.State>
            get() = MutableStateFlow(initialState).asStateFlow()
        override val event: SharedFlow<PostDetailContract.Event>
            get() = MutableSharedFlow()
        override val effect: Flow<PostDetailContract.Effect>
            get() = Channel<PostDetailContract.Effect>().receiveAsFlow()

        override fun setEvent(event: PostDetailContract.Event) {
        }
    }

    PostDetailView(rememberNavController(), viewModel)
}