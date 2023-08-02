package com.luigidev.himnosycorosmiepiadmin.form.domain.usecase

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.form.data.FormRepositoryImp
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.Choir

class UploadChoirUseCase {

    private val repository = FormRepositoryImp()

    suspend operator fun invoke(choir: Choir): ResultAPI<String> = repository.uploadChoir(choir)
}