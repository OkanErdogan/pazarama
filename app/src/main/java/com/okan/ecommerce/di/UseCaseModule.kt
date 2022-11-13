package com.okan.ecommerce.di

import com.okan.ecommerce.domain.repository.LoginRepository
import com.okan.ecommerce.domain.repository.ProductListRepository
import com.okan.ecommerce.domain.usecase.LoginUseCase
import com.okan.ecommerce.domain.usecase.ProductListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideLoginUseCase(loginRepository: LoginRepository): LoginUseCase {
        return LoginUseCase(loginRepository)
    }

    @Singleton
    @Provides
    fun provideProductListUseCase(productListRepository: ProductListRepository): ProductListUseCase {
        return ProductListUseCase(productListRepository)
    }
}