package com.okan.ecommerce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okan.ecommerce.data.BaseApplication
import com.okan.ecommerce.domain.models.LoginRequest
import com.okan.ecommerce.domain.models.LoginResponse
import com.okan.ecommerce.domain.usecase.LoginUseCase
import com.okan.ecommerce.util.SharedPrefUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    var loginResponseLiveData = MutableLiveData<LoginResponse>()

    fun login(username: String, password: String): LiveData<LoginResponse> {
        viewModelScope.launch {
            val loginResponse = loginUseCase.login(
                LoginRequest(
                    loginUseCase.encodeUserPassForBasicAuth(
                        username,
                        password
                    )
                )
            )
            SharedPrefUtil.setPassKey(
                BaseApplication.getAppInstance().applicationContext,
                loginResponse.passKey
            )
            loginResponseLiveData.value = loginResponse
        }

        return loginResponseLiveData
    }
}