package com.luigidev.himnosycorosmiepiadmin.form.domain.repository

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.Choir

interface IFormRepository {

    suspend fun uploadChoir(choir: Choir): ResultAPI<String>
}