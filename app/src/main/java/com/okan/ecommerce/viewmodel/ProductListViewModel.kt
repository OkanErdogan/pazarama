package com.okan.ecommerce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okan.ecommerce.data.BaseApplication
import com.okan.ecommerce.domain.models.ProductListRequest
import com.okan.ecommerce.domain.models.ProductListResponse
import com.okan.ecommerce.domain.usecase.ProductListUseCase
import com.okan.ecommerce.util.SharedPrefUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val productListUseCase: ProductListUseCase) :
    ViewModel() {
    private var productListResponseLiveData = MutableLiveData<ProductListResponse>()
    private var page = 0

    fun nextPage(): LiveData<ProductListResponse> {
        page++
        return getProductList()
    }

    private fun getProductList(): LiveData<ProductListResponse> {
        viewModelScope.launch {
            productListResponseLiveData.value = productListUseCase.getProductList(
                ProductListRequest(
                    SharedPrefUtil.getPassKey(BaseApplication.getAppInstance().applicationContext)
                )
            )
        }
        return productListResponseLiveData
    }
}