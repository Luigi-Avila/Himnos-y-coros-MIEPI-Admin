package com.luigidev.himnosycorosmiepiadmin.form.data

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.form.data.network.FormService
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.form.domain.repository.IFormRepository

class FormRepositoryImp: IFormRepository {

    private val service = FormService()


    override suspend fun uploadChoir(choir: Choir): ResultAPI<String> = service.uploadChoir(choir)
}