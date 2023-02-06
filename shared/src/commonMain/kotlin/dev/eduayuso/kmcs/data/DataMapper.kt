package dev.eduayuso.kmcs.data

import dev.eduayuso.kmcs.data.model.*
import dev.eduayuso.kmcs.domain.entities.*

fun PostResponse.map() = PostEntity(
    id = this.id,
    text = this.text,
    image = this.image,
    likes = this.likes,
    publishDate = this.publishDate,
    owner = this.owner?.map()
)

fun UserResponse.map() = UserEntity(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    picture = this.picture,
)

fun PostListResponse.map() =

    this.data?.map { it.map() }

fun UserListResponse.map() =

    this.data?.map { it.map() }

fun CommentResponse.map() = CommentEntity(
    id = this.id,
    owner = this.owner?.map(),
    message = this.message,
    publishDate = this.publishDate
)

fun CommentListResponse.map() =

    this.data?.map { it.map() }