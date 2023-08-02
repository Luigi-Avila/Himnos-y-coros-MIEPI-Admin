package com.luigidev.himnosycorosmiepiadmin.form.data.network

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.Choir

class FormService {

    private val client = FormClient()

   suspend fun uploadChoir(choir: Choir): ResultAPI<String> = client.uploadChoir(choir)

}