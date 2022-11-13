package com.okan.ecommerce.domain.models

data class LoginResponse(
    val passKey: String,
    val success: Boolean,
    val message: String,
)