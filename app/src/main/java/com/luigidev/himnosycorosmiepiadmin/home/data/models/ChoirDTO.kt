package com.luigidev.himnosycorosmiepiadmin.home.data.models

data class ChoirDTO(
    val id: String,
    val title: String,
    val lyrics: String,
    val internetThumbnail: String?,
    val localThumbnail: String?,
    val video: String?,
    val choirNumber: Int
)
