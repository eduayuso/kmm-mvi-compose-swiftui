package dev.eduayuso.kmcs.android.features.posts.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.zIndex
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import dev.eduayuso.kmcs.domain.entities.PostEntity

@ExperimentalCoilApi
@Composable
fun PostItemView(
    post: PostEntity,
    onClick: () -> Unit
) {

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 2.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Column {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberImagePainter(post.owner?.picture),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .clip(CircleShape)
                        .width(56.dp)
                        .height(56.dp)
                )
                Column {
                    Text(
                        text = "${post.owner?.firstName ?: ""} ${post.owner?.lastName ?: ""}",
                        color = Color.Gray,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = post.publishDate ?: "",
                        color = Color.Gray
                    )
                }
            }

            Image(
                painter = rememberImagePainter(post.image),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
        }
    }
}

@Preview
@Composable
fun PostItemViewPreview() {

    val character = PostEntity(
        id = "1",
        text = "Toxic Rick",
        publishDate = "01-02-2000",
        image = "https://rickandmortyapi.com/api/character/avatar/361.jpeg"
    )

    PostItemView(character) { }
}