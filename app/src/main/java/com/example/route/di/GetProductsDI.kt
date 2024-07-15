package com.example.route.di

import com.example.route.data.api.ApiServices
import com.example.route.data.repository.ProductRemoteDS
import com.example.route.data.repository.ProductRepository
import com.example.route.domain.interactor.GetProductsUC
import com.example.route.domain.repository.IProductRepository
import com.example.route.domain.repository.remote.IProductRemoteDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object GetProductsDI {
    @Provides
    fun provideGetProductsUC(repository: IProductRepository): GetProductsUC {
        return GetProductsUC(repository)
    }

    @Provides
    fun provideProductRepository(remoteDS: IProductRemoteDS): IProductRepository {
        return ProductRepository(remoteDS)
    }

    @Provides
    fun provideProductRemoteDS(apiServices: ApiServices): IProductRemoteDS {
        return ProductRemoteDS(apiServices)
    }
}