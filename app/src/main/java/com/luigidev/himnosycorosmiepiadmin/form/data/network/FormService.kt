package com.luigidev.himnosycorosmiepiadmin.form.data.network

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.Choir
import javax.inject.Inject

class FormService @Inject constructor(private val client: FormClient) {

//    private val client = FormClient()

   suspend fun uploadChoir(choir: Choir): ResultAPI<String> = client.uploadChoir(choir)

}