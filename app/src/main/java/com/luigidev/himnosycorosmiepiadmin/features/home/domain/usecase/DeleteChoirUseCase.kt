package com.luigidev.himnosycorosmiepiadmin.features.home.domain.usecase

import com.luigidev.himnosycorosmiepiadmin.features.home.data.utils.HomeResultAPI
import com.luigidev.himnosycorosmiepiadmin.features.home.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.features.home.domain.repository.IHomeRepository
import javax.inject.Inject

class DeleteChoirUseCase @Inject constructor(private val repository: IHomeRepository) {

    operator fun invoke(choirData: Choir, apiState: (HomeResultAPI<String>) -> Unit) {
        if (choirData.storagePath.isNullOrBlank()) {
            repository.deleteChoir(choirData.id, apiState)
        } else {
            repository.deleteChoirWithImage(
                id = choirData.id,
                storagePath = choirData.storagePath,
                apiState
            )
        }
    }
}