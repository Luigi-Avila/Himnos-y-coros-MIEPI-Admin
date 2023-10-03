package com.luigidev.himnosycorosmiepiadmin.form.domain.models

import android.net.Uri

data class Choir(
    val id: String = "",
    val title: String = "",
    val lyrics: String = "",
    val localThumbnail: Uri? = null,
    val internetThumbnail: String? = null,
    val video: String? = null,
    val choirNumber: Int = 0,
    val storagePath: String? = null
)
