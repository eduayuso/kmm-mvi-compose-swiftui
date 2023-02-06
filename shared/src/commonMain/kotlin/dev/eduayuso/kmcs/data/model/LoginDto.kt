package dev.eduayuso.kmcs.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(

    val username: String,
    val password: String
)