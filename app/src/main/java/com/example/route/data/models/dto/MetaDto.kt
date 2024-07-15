package com.example.route.data.models.dto

import com.google.gson.annotations.SerializedName

data class MetaDto(
	@field:SerializedName("createdAt") val createdAt: String? = null,
	@field:SerializedName("qrCode") val qrCode: String? = null,
	@field:SerializedName("barcode") val barcode: String? = null,
	@field:SerializedName("updatedAt") val updatedAt: String? = null
)