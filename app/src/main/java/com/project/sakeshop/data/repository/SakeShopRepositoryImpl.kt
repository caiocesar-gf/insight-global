package com.project.sakeshop.data.repository

import com.project.sakeshop.Resource
import com.project.sakeshop.domain.model.SakeShop
import com.project.sakeshop.domain.repository.SakeShopRepository
import com.project.sakeshop.data.local.datasource.SakeShopDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SakeShopRepositoryImpl @Inject constructor(
    private val dataSource: SakeShopDataSource
) : SakeShopRepository {

    override fun getAllSakeShops(): Flow<Resource<List<SakeShop>>> = flow {
        emit(Resource.Loading())

        try {
            val sakeShops = dataSource.getSakeShops().map { it.toDomain() }
            emit(Resource.Success(sakeShops))
        } catch (e: Exception) {
            emit(Resource.Error("Erro reading stores: ${e.localizedMessage}"))
        }
    }


    override fun getSakeShopById(id: Int): Flow<Resource<SakeShop>> = flow {
        emit(Resource.Loading())

        try {
            val sakeShops = dataSource.getSakeShops()
            if (id >= 0 && id < sakeShops.size) {
                emit(Resource.Success(sakeShops[id].toDomain()))
            } else {
                emit(Resource.Error("Store not found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }
}