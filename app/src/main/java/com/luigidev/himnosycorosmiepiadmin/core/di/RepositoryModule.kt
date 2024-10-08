package com.luigidev.himnosycorosmiepiadmin.core.di

import com.luigidev.himnosycorosmiepiadmin.form.data.FormRepositoryImp
import com.luigidev.himnosycorosmiepiadmin.form.domain.repository.IFormRepository
import com.luigidev.himnosycorosmiepiadmin.home.data.HomeRepositoryImp
import com.luigidev.himnosycorosmiepiadmin.home.domain.repository.IHomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindFormRepository(
        formRepositoryImp: FormRepositoryImp
    ): IFormRepository

    @Binds
    abstract fun bindHomeRepository(
        homeRepositoryImp: HomeRepositoryImp
    ): IHomeRepository
}