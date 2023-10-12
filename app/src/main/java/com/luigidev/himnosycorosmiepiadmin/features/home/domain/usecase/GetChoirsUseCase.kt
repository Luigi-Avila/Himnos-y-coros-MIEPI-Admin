package com.luigidev.himnosycorosmiepiadmin.features.home.domain.usecase

import com.luigidev.himnosycorosmiepiadmin.features.home.data.utils.HomeResultAPI
import com.luigidev.himnosycorosmiepiadmin.features.home.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.features.home.domain.repository.IHomeRepository
import javax.inject.Inject

class GetChoirsUseCase @Inject constructor(private val repository: IHomeRepository) {

    operator fun invoke(apiState: (HomeResultAPI<List<Choir>>) -> Unit) =
        repository.getChoirs(apiState)
}