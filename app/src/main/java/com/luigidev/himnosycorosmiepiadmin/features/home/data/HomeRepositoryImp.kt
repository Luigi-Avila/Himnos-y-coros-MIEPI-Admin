package com.luigidev.himnosycorosmiepiadmin.features.home.data

import com.luigidev.himnosycorosmiepiadmin.features.home.data.network.HomeService
import com.luigidev.himnosycorosmiepiadmin.features.home.data.utils.HomeResultAPI
import com.luigidev.himnosycorosmiepiadmin.features.home.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.features.home.domain.repository.IHomeRepository
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(private val service: HomeService) : IHomeRepository {

    override fun getChoirs(apiState: (HomeResultAPI<List<Choir>>) -> Unit) =
        service.getChoirs(apiState)

    override fun deleteChoir(id: String, apiState: (HomeResultAPI<String>) -> Unit) =
        service.deleteChoir(id, apiState)

    override fun deleteChoirWithImage(
        id: String,
        storagePath: String,
        apiState: (HomeResultAPI<String>) -> Unit
    ) = service.deleteChoirWithImage(id, storagePath, apiState)
}