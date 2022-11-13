package com.okan.ecommerce.domain.usecase

import com.okan.ecommerce.domain.models.ProductListRequest
import com.okan.ecommerce.domain.models.ProductListResponse
import com.okan.ecommerce.domain.repository.ProductListRepository
import javax.inject.Inject

class ProductListUseCase @Inject constructor(private val productListRepository: ProductListRepository) {

    suspend fun getProductList(productListRequest: ProductListRequest): ProductListResponse {
        return productListRepository.getProductList(productListRequest)
    }
}