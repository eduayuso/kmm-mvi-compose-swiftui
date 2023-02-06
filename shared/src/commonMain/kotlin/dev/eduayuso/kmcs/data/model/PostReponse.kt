package dev.eduayuso.kmcs.data.model

import kotlinx.serialization.Serializable

@Serializable
class PostResponse(

    var id: String,
    var text: String? = null,
    var image: String? = null,
    var likes: Int? = null,
    var publishDate: String? = null,
    var tags: List<String>? = null,
    var owner: UserResponse? = null
)

@Serializable
data class PostListResponse(

    val data: List<PostResponse>? = null,
    val total: Int? = null,
    val page: Int? = null
)