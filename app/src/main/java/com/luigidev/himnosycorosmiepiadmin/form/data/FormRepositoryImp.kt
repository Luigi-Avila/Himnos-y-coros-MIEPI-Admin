package com.luigidev.himnosycorosmiepiadmin.form.data

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.form.data.network.FormService
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.ChoirFillOut
import com.luigidev.himnosycorosmiepiadmin.form.domain.repository.IFormRepository
import javax.inject.Inject

class FormRepositoryImp @Inject constructor(private val service: FormService) : IFormRepository {
    override fun uploadChoir(
        choir: Choir,
        uploadingState: (ResultAPI<String>) -> Unit
    ) = service.uploadChoir(choir, uploadingState)

    override fun uploadChoirWithImage(
        choir: Choir,
        uploadingState: (ResultAPI<String>) -> Unit
    ) = service.uploadChoirWithImage(choir, uploadingState)

    override fun getChoirById(
        id: String,
        resultState: (ResultAPI<ChoirFillOut>) -> Unit
    ) = service.getChoirById(id, resultState)

    override fun updateChoir(
        id: String,
        choir: Choir,
        resultState: (ResultAPI<String>) -> Unit
    ) = service.updateChoir(id, choir, resultState)

    override fun updateChoirWithImage(
        id: String,
        choir: Choir,
        uploadingState: (ResultAPI<String>) -> Unit
    ) = service.updateChoirWithImage(id, choir, uploadingState)
}