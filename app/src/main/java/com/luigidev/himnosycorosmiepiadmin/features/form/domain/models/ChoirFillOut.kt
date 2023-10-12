package com.luigidev.himnosycorosmiepiadmin.features.form.domain.models

data class ChoirFillOut(
    val id: String = "",
    val title: String = "",
    val lyrics: String = "",
    val thumbnail: String? = null,
    val video: String? = null,
    val choirNumber: Int = 0,
    val storagePath: String? = null
)
