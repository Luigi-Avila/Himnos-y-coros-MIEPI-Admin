package com.luigidev.himnosycorosmiepiadmin.form.domain.usecase

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.form.domain.repository.IFormRepository
import javax.inject.Inject

class DeleteChoirImageUseCase @Inject constructor(private val repository: IFormRepository) {
    operator fun invoke(
        id: String,
        storagePath: String,
        resultState: (ResultAPI<String>) -> Unit
    ) = repository.deleteImageFromStorage(id, storagePath, resultState)
}