package com.project.sakeshop.domain.useCase

import com.project.sakeshop.Resource
import com.project.sakeshop.domain.model.SakeShop
import com.project.sakeshop.domain.repository.SakeShopRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSakeShopsUseCase @Inject constructor(
    private val repository: SakeShopRepository
) {
    operator fun invoke(): Flow<Resource<List<SakeShop>>> {
        return repository.getAllSakeShops()
    }
}