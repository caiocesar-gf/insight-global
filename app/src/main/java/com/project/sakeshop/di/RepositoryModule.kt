package com.project.sakeshop.di

import com.project.sakeshop.data.repository.SakeShopRepositoryImpl
import com.project.sakeshop.domain.repository.SakeShopRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindSakeShopRepository(
        repositoryImpl: SakeShopRepositoryImpl
    ): SakeShopRepository
}