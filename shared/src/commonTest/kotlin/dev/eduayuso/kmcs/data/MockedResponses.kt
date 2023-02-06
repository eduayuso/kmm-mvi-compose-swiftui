package dev.eduayuso.kmcs.data

import dev.eduayuso.kmcs.data.model.PostListResponse
import dev.eduayuso.kmcs.data.model.PostResponse
import dev.eduayuso.kmcs.data.model.UserListResponse
import dev.eduayuso.kmcs.data.model.UserResponse
import dev.eduayuso.kmcs.domain.entities.UserEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object MockedResponses {

    val userResponse = UserResponse(id = "1", firstName = "First")
    val userEntity = UserEntity(id = "1", firstName = "First")
    val userJsonResponse = Json.encodeToString(userResponse)

    val postResponse = PostResponse(id = "1", text = "Hola")
    val postJsonResponse = Json.encodeToString(postResponse)

    val userListJsonResponse = Json.encodeToString(
        UserListResponse(
            data = listOf(userResponse)
        )
    )

    val postListJsonResponse = Json.encodeToString(
        PostListResponse(
            data = listOf(postResponse)
        )
    )
}