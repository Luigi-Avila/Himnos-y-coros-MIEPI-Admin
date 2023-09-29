package com.luigidev.himnosycorosmiepiadmin.form.data.utils

import com.luigidev.himnosycorosmiepiadmin.form.data.models.ChoirDTO
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.ChoirFillOut

fun ChoirDTO.toDomainFillOut(): ChoirFillOut =
    ChoirFillOut(
        id = this.id ?: "",
        title = this.title ?: "",
        lyrics = this.lyrics ?: "",
        thumbnail = this.internetThumbnail ?: this.internetThumbnail,
        video = this.video ?: "",
        choirNumber = this.choirNumber ?: 0,
    )