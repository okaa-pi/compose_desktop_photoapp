package model

import kotlinx.serialization.Serializable

@Serializable
data class Photo (
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)