package com.luigidev.himnosycorosmiepiadmin.home.data.utils

import com.google.firebase.firestore.DocumentSnapshot
import com.luigidev.himnosycorosmiepiadmin.home.domain.models.Choir

fun DocumentSnapshot.toDomain(): Choir =
    Choir(
        id = this.data?.get("id").toString(),
        choirNumber = this.data?.get("choirNumber").toString().toInt(),
        title = this.data?.get("title").toString(),
        lyrics = this.data?.get("lyrics").toString()
    )