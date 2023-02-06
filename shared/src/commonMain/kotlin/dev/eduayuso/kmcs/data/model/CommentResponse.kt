package dev.eduayuso.kmcs.data.model

import kotlinx.serialization.Serializable

@Serializable
class CommentResponse(

    val id: String? = null,
    val owner: UserResponse? = null,
    val message: String? = null,
    val publishDate: String? = null
)

@Serializable
data class CommentListResponse(

    val data: List<CommentResponse>? = null,
    val total: Int? = null,
    val page: Int? = null
)