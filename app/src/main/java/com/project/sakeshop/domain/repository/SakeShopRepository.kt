package com.project.sakeshop.domain.repository

import com.project.sakeshop.Resource
import com.project.sakeshop.domain.model.SakeShop
import kotlinx.coroutines.flow.Flow

interface SakeShopRepository {
    fun getAllSakeShops(): Flow<Resource<List<SakeShop>>>
    fun getSakeShopById(id: Int): Flow<Resource<SakeShop>>
}