package com.okan.ecommerce.domain.repository

import com.okan.ecommerce.domain.models.LoginRequest
import com.okan.ecommerce.domain.models.LoginResponse

interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest): LoginResponse
}