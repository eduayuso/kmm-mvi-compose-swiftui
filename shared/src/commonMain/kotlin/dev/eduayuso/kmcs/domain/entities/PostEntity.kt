package dev.eduayuso.kmcs.domain.entities

import kotlinx.serialization.Serializable

@Serializable
class PostEntity(

    var id: String,
    var text: String? = null,
    var image: String? = null,
    var likes: Int? = null,
    var publishDate: String? = null,
    var tags: List<String>? = null,
    var owner: UserEntity? = null

): IEntity