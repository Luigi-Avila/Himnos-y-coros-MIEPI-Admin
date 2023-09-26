package com.luigidev.himnosycorosmiepiadmin.home.data.utils

import com.google.firebase.firestore.DocumentSnapshot
import com.luigidev.himnosycorosmiepiadmin.home.data.models.ChoirDTO
import com.luigidev.himnosycorosmiepiadmin.home.domain.models.Choir

fun ChoirDTO.toDomain(): Choir =
    Choir(
        id = this.id,
        choirNumber = this.choirNumber,
        title = this.title,
        lyrics = this.lyrics,
        thumbnail = this.internetThumbnail ?: this.localThumbnail
    )

fun DocumentSnapshot.toDTO(): ChoirDTO =
    ChoirDTO(
        id = this.data?.get("id").toString(),
        title = this.data?.get("title").toString(),
        lyrics = this.data?.get("lyrics").toString(),
        internetThumbnail = this.get("internetThumbnail").toString(),
        localThumbnail = this.get("localThumbnail").toString(),
        video = this.get("video").toString(),
        choirNumber = this.get("choirNumber").toString().toInt()
    )