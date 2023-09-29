package com.luigidev.himnosycorosmiepiadmin.form.domain.usecase

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.ChoirFillOut
import com.luigidev.himnosycorosmiepiadmin.form.domain.repository.IFormRepository
import javax.inject.Inject

class GetChoirByIdUseCase @Inject constructor(private val repository: IFormRepository) {
    operator fun invoke(id: String, resultState: (ResultAPI<ChoirFillOut>) -> Unit) =
        repository.getChoirById(id, resultState)
}