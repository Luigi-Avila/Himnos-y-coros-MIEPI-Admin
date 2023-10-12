package com.luigidev.himnosycorosmiepiadmin.features.home.data.network

import com.luigidev.himnosycorosmiepiadmin.features.home.data.utils.HomeResultAPI
import com.luigidev.himnosycorosmiepiadmin.features.home.data.utils.toDomain
import com.luigidev.himnosycorosmiepiadmin.features.home.domain.models.Choir
import javax.inject.Inject

class HomeService @Inject constructor(private val client: HomeClient) {

    fun getChoirs(apiState: (HomeResultAPI<List<Choir>>) -> Unit) {
        client.getChoirs { result ->
            when (result) {
                is HomeResultAPI.Error -> apiState.invoke(HomeResultAPI.Error("Not found"))
                is HomeResultAPI.Success -> apiState.invoke(HomeResultAPI.Success(result.data.map { it.toDomain() }))
            }
        }
    }

    fun deleteChoir(
        id: String,
        apiState: (HomeResultAPI<String>) -> Unit) = client.deleteChoir(id, apiState)

    fun deleteChoirWithImage(
        id: String,
        storagePath: String,
        apiState: (HomeResultAPI<String>) -> Unit
    ) = client.deleteChoirWithImage(id, storagePath, apiState)


}