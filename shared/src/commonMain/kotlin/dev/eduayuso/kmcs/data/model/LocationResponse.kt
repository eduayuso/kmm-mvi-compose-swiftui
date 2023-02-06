package dev.eduayuso.kmcs.data.model

import kotlinx.serialization.Serializable

@Serializable
class LocationResponse(

    val state: String? = null,
    val street: String? = null,
    val city: String? = null,
    val timezone: String? = null,
    val country: String? = null
)