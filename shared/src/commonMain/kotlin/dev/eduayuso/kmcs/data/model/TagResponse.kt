package dev.eduayuso.kmcs.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TagListResponse(

    val data: List<String>? = null,
    val total: Int? = null,
    val page: Int? = null
)