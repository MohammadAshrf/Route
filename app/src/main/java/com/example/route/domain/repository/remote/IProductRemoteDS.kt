package com.example.route.domain.repository.remote

import com.example.route.data.models.dto.ProductDto

interface IProductRemoteDS {
    suspend fun getProducts(): List<ProductDto>
}