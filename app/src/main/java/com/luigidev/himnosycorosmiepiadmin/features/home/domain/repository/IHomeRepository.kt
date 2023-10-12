package com.luigidev.himnosycorosmiepiadmin.features.home.domain.repository

import com.luigidev.himnosycorosmiepiadmin.features.home.data.utils.HomeResultAPI
import com.luigidev.himnosycorosmiepiadmin.features.home.domain.models.Choir

interface IHomeRepository {
   fun getChoirs( apiState: (HomeResultAPI<List<Choir>>) -> Unit)
   fun deleteChoir(id: String, apiState: (HomeResultAPI<String>) -> Unit)
   fun deleteChoirWithImage(id: String, storagePath: String, apiState: (HomeResultAPI<String>) -> Unit)
}