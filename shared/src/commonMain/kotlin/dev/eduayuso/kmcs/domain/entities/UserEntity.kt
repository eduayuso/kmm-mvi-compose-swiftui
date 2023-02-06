package dev.eduayuso.kmcs.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(

    var id: String,
    var firstName: String? = null,
    var lastName: String? = null,
    var picture: String? = null

): IEntity