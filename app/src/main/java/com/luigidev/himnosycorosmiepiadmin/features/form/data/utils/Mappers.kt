package com.luigidev.himnosycorosmiepiadmin.features.form.data.utils

import com.luigidev.himnosycorosmiepiadmin.features.form.data.models.ChoirDTO
import com.luigidev.himnosycorosmiepiadmin.features.form.domain.models.ChoirFillOut

fun ChoirDTO.toDomainFillOut(): ChoirFillOut =
    ChoirFillOut(
        id = this.id ?: "",
        title = this.title ?: "",
        lyrics = this.lyrics ?: "",
        thumbnail = this.internetThumbnail ?: this.localThumbnail,
        video = this.video ?: "",
        storagePath = this.storagePath ?: "",
        choirNumber = this.choirNumber ?: 0,
    )