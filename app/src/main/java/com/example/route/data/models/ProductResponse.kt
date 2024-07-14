package com.example.route.data.models

import com.google.gson.annotations.SerializedName

data class ProductResponse(
	@field:SerializedName("total") val total: Int? = null,
	@field:SerializedName("limit") val limit: Int? = null,
	@field:SerializedName("skip") val skip: Int? = null,
	@field:SerializedName("products") val products: List<ProductDto?>? = null
)