package com.luigidev.himnosycorosmiepiadmin.form.data.network

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.Choir
import javax.inject.Inject

class FormService @Inject constructor(private val client: FormClient) {


    suspend fun uploadChoir(
        choir: Choir,
        uploadingState: (ResultAPI<String>) -> Unit
    ) = client.uploadChoir(choir, uploadingState)

    suspend fun uploadChoirWithImage(
        choir: Choir,
        state: (ResultAPI<String>) -> Unit
    ) =
        client.uploadChoirWithImage(choir, state)

}