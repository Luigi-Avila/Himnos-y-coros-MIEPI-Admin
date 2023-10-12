package com.luigidev.himnosycorosmiepiadmin.features.form.domain.repository

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.features.form.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.features.form.domain.models.ChoirFillOut

interface IFormRepository {

    fun uploadChoir(choir: Choir, uploadingState: (ResultAPI<String>) -> Unit)

    fun uploadChoirWithImage(choir: Choir, uploadingState: (ResultAPI<String>) -> Unit)

    fun getChoirById(id: String, resultState: (ResultAPI<ChoirFillOut>) -> Unit)

    fun updateChoir(id: String, choir: Choir, resultState: (ResultAPI<String>) -> Unit)

    fun updateChoirWithImage(id: String, choir: Choir, uploadingState: (ResultAPI<String>) -> Unit)

    fun deleteImageFromStorage(id: String, storagePath: String, resultState: (ResultAPI<String>) -> Unit)
}