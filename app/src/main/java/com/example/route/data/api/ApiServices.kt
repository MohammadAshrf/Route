package com.example.route.data.api

import com.example.route.data.models.dto.ProductResponse
import retrofit2.http.GET

interface ApiServices {
    @GET("products")
    suspend fun getProducts(): ProductResponse
}