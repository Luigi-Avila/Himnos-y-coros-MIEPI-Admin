package com.luigidev.himnosycorosmiepiadmin.home.domain.repository

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.home.domain.models.Choir

interface IHomeRepository {

    suspend fun getChoirs(): ResultAPI<List<Choir?>>
}