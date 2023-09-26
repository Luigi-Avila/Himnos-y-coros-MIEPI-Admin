package com.luigidev.himnosycorosmiepiadmin.home.data

import com.luigidev.himnosycorosmiepiadmin.home.data.network.HomeService
import com.luigidev.himnosycorosmiepiadmin.home.data.utils.HomeResultAPI
import com.luigidev.himnosycorosmiepiadmin.home.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.home.domain.repository.IHomeRepository
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(private val service: HomeService) : IHomeRepository {

    override fun getChoirs(apiState: (HomeResultAPI<List<Choir>>) -> Unit) =
        service.getChoirs(apiState)

}