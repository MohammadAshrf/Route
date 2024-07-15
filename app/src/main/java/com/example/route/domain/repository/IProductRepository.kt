package com.example.route.domain.repository

import com.example.route.domain.models.Product

interface IProductRepository {
    suspend fun getProductsRemote(): List<Product>
}