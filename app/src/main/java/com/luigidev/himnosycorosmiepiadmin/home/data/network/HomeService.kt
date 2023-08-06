package com.luigidev.himnosycorosmiepiadmin.home.data.network

import android.util.Log
import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.home.data.utils.toDomain
import com.luigidev.himnosycorosmiepiadmin.home.domain.models.Choir

class HomeService {

    private val client = HomeClient()

    suspend fun getChoirs(): ResultAPI<List<Choir?>> {
        Log.i("Service", "Entro al proceso")
      return when(val result = client.getChoirs()){
          is ResultAPI.Error -> {
              Log.i("Service", "Entro a error ${result.message}")
              ResultAPI.Error(result.message)
          }
          is ResultAPI.Success -> {
              val response = result.data.map { it.toDomain() }
              Log.i("Response", "$response")
              ResultAPI.Success(response)
          }
      }
    }



}