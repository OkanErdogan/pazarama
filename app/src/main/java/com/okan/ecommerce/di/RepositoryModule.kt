package com.okan.ecommerce.di
import com.okan.ecommerce.data.repository.LoginRepositoryImpl
import com.okan.ecommerce.data.repository.ProductListRepositoryImpl
import com.okan.ecommerce.domain.repository.LoginRepository
import com.okan.ecommerce.domain.repository.ProductListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideLoginRepository(): LoginRepository {
        return LoginRepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideProductListRepository(): ProductListRepository {
        return ProductListRepositoryImpl()
    }
}