package com.luigidev.himnosycorosmiepiadmin.home.domain.models

data class Choir(
    val id: String,
    val choirNumber: Int,
    val title: String,
    val lyrics: String,
    val thumbnail: String?,
    val storagePath: String?
)
