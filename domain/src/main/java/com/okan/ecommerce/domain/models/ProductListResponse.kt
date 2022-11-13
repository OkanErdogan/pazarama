package com.okan.ecommerce.domain.models

data class ProductListResponse(
    val success: Boolean,
    val message: String,
    val productItemList : ArrayList<ProductItem>
)