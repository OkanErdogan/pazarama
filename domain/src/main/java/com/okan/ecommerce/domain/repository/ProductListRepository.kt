package com.okan.ecommerce.domain.repository

import com.okan.ecommerce.domain.models.ProductListRequest
import com.okan.ecommerce.domain.models.ProductListResponse

interface ProductListRepository {
    suspend fun getProductList(productListRequest: ProductListRequest) : ProductListResponse
}