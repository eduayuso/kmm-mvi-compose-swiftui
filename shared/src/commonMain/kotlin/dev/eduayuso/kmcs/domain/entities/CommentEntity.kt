package dev.eduayuso.kmcs.domain.entities

import kotlinx.serialization.Serializable

@Serializable
class CommentEntity(

    val id: String? = null,
    val owner: UserEntity? = null,
    val message: String? = null,
    val publishDate: String? = null

): IEntity