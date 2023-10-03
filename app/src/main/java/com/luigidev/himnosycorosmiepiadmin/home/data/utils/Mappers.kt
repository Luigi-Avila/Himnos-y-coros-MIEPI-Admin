package com.luigidev.himnosycorosmiepiadmin.home.data.utils

import com.luigidev.himnosycorosmiepiadmin.home.data.models.ChoirDTO
import com.luigidev.himnosycorosmiepiadmin.home.domain.models.Choir

fun ChoirDTO.toDomain(): Choir =
    Choir(
        id = this.id ?: "",
        choirNumber = this.choirNumber ?: 0,
        title = this.title ?: "",
        lyrics = this.lyrics ?: "",
        thumbnail = if (this.internetThumbnail.isNullOrEmpty()) this.localThumbnail else this.internetThumbnail,
        storagePath = if (this.internetThumbnail.isNullOrEmpty()) this.storagePath else null
    )