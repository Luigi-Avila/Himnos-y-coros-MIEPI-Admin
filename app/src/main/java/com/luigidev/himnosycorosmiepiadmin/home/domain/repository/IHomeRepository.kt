package com.luigidev.himnosycorosmiepiadmin.home.domain.repository

import com.luigidev.himnosycorosmiepiadmin.home.data.utils.HomeResultAPI
import com.luigidev.himnosycorosmiepiadmin.home.domain.models.Choir

interface IHomeRepository {
   fun getChoirs( apiState: (HomeResultAPI<List<Choir>>) -> Unit)
}