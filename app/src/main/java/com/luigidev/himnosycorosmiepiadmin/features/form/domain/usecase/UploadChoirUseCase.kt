package com.luigidev.himnosycorosmiepiadmin.features.form.domain.usecase

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.features.form.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.features.form.domain.repository.IFormRepository
import javax.inject.Inject

class UploadChoirUseCase
    @Inject
    constructor(private val repository: IFormRepository) {
        operator fun invoke(
            choir: Choir,
            state: (ResultAPI<String>) -> Unit,
        ) {
            return if (choir.localThumbnail != null) {
                repository.uploadChoirWithImage(choir, state)
            } else {
                repository.uploadChoir(choir, state)
            }
        }
    }