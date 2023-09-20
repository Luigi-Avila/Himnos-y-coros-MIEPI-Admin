package com.luigidev.himnosycorosmiepiadmin.home.domain.usecase

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.home.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.home.domain.repository.IHomeRepository
import javax.inject.Inject

class GetChoirsUseCase @Inject constructor( private val repository: IHomeRepository) {

//    private val repository = HomeRepositoryImp()

    suspend operator fun invoke(): ResultAPI<List<Choir?>> = repository.getChoirs()
}