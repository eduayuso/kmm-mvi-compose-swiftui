package dev.eduayuso.kmcs.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class TagEntity(

    var id: String? = null

): IEntity