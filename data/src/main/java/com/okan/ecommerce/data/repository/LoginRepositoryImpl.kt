package com.okan.ecommerce.data.repository

import com.okan.ecommerce.data.BaseApplication
import com.okan.ecommerce.data.R
import com.okan.ecommerce.domain.models.LoginRequest
import com.okan.ecommerce.domain.models.LoginResponse
import com.okan.ecommerce.domain.repository.LoginRepository

class LoginRepositoryImpl : LoginRepository {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        val token = BaseApplication.getAppInstance().applicationContext.getString(R.string.token)
        val passKey = BaseApplication.getAppInstance().applicationContext.getString(R.string.passkey)
        val isSuccess = token.equals(loginRequest.token)
        var message = if (isSuccess) "Başarılı" else "Başarısız"
        return LoginResponse(passKey, isSuccess, message)
    }
}