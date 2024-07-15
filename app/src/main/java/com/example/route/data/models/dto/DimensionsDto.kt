package com.example.route.data.models.dto

import com.google.gson.annotations.SerializedName

data class DimensionsDto(
	@field:SerializedName("depth") val depth: Double? = null,
	@field:SerializedName("width") val width: Double? = null,
	@field:SerializedName("height") val height: Double? = null
)