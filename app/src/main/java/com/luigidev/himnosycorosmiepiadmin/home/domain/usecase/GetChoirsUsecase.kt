package com.luigidev.himnosycorosmiepiadmin.home.domain.usecase

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.home.data.HomeRepositoryImp
import com.luigidev.himnosycorosmiepiadmin.home.domain.models.Choir

class GetChoirsUsecase {

    private val repository = HomeRepositoryImp()

    suspend operator fun invoke(): ResultAPI<List<Choir?>> = repository.getChoirs()
}