package com.project.sakeshop.domain.useCase

import com.project.sakeshop.Resource
import com.project.sakeshop.domain.model.SakeShop
import com.project.sakeshop.domain.repository.SakeShopRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSakeShopByIdUseCase @Inject constructor(
    private val repository: SakeShopRepository
) {
    operator fun invoke(id: Int): Flow<Resource<SakeShop>> {
        return repository.getSakeShopById(id)
    }
}