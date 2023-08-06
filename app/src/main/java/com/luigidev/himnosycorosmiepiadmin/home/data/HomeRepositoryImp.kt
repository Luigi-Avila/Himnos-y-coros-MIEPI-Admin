package com.luigidev.himnosycorosmiepiadmin.home.data

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.home.data.network.HomeService
import com.luigidev.himnosycorosmiepiadmin.home.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.home.domain.repository.IHomeRepository

class HomeRepositoryImp: IHomeRepository {

    private val service = HomeService()

    override suspend fun getChoirs():ResultAPI<List<Choir?>> = service.getChoirs()

}