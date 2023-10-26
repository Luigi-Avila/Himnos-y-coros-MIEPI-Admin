package com.luigidev.himnosycorosmiepiadmin.features.form.domain.usecase

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.features.form.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.features.form.domain.repository.IFormRepository
import javax.inject.Inject

class UpdateChoirUseCase
    @Inject
    constructor(private val repository: IFormRepository) {
        operator fun invoke(
            id: String,
            choir: Choir,
            uploadingState: (ResultAPI<String>) -> Unit,
        ) = if (choir.localThumbnail != null) {
            repository.updateChoirWithImage(id, choir, uploadingState)
        } else {
            repository.updateChoir(id, choir, uploadingState)
        }
    }