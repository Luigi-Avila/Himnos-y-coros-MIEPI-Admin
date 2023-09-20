package com.luigidev.himnosycorosmiepiadmin.home.data.network

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.home.data.utils.toDomain
import com.luigidev.himnosycorosmiepiadmin.home.domain.models.Choir
import javax.inject.Inject

class HomeService @Inject constructor(private val client: HomeClient) {

//    private val client = HomeClient()

    suspend fun getChoirs(): ResultAPI<List<Choir?>> {
        return when (val result = client.getChoirs()) {
            is ResultAPI.Error -> {
                ResultAPI.Error(result.message)
            }

            is ResultAPI.Success -> {
                val response = result.data.map { it.toDomain() }
                ResultAPI.Success(response)
            }
        }
    }


}