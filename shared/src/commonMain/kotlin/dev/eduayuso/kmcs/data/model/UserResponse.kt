package dev.eduayuso.kmcs.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(

    var id: String,
    var firstName: String? = null,
    var lastName: String? = null,
    var picture: String? = null
)

@Serializable
data class UserListResponse(

    val data: List<UserResponse>? = null,
    val total: Int? = null,
    val page: Int? = null
)