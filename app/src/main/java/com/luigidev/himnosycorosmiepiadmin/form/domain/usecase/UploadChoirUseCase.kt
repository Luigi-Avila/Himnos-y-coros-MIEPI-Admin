package com.luigidev.himnosycorosmiepiadmin.form.domain.usecase

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.form.domain.repository.IFormRepository
import javax.inject.Inject

class UploadChoirUseCase @Inject constructor( private val repository: IFormRepository) {

//    private val repository = FormRepositoryImp()

    suspend operator fun invoke(choir: Choir): ResultAPI<String> = repository.uploadChoir(choir)
}