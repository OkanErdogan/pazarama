package com.okan.ecommerce.domain.usecase

import com.okan.ecommerce.domain.models.LoginRequest
import com.okan.ecommerce.domain.models.LoginResponse
import com.okan.ecommerce.domain.repository.LoginRepository
import org.apache.commons.codec.binary.Base64
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return loginRepository.login(loginRequest)
    }

    fun encodeUserPassForBasicAuth(userName: String, password: String): String {
        val autStr: String = userName + ":" + password
        return "Basic " + Base64.encodeBase64String(autStr.toByteArray()).trim()
    }
}