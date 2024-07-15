package com.example.route.data.repository

import com.example.route.data.api.ApiServices
import com.example.route.data.models.dto.ProductDto
import com.example.route.domain.repository.remote.IProductRemoteDS

internal class ProductRemoteDS(private val apiServices: ApiServices) : IProductRemoteDS {
    override suspend fun getProducts(): List<ProductDto> {
        return apiServices.getProducts().products ?: emptyList()
    }
}