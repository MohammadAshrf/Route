package com.example.route.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
	val images: List<String>,
	val thumbnail: String,
	val minimumOrderQuantity: Int,
	val rating: Double,
	val returnPolicy: String,
	val description: String,
	val weight: Int,
	val warrantyInformation: String,
	val title: String,
	val tags: List<String>,
	val discountPercentage: Double,
	val discountedPrice: Double,
	val reviews: List<Reviews>,
	val price: Double,
	val meta: Meta,
	val shippingInformation: String,
	val id: Int,
	val availabilityStatus: String,
	val category: String,
	val stock: Int,
	val sku: String,
	val dimensions: Dimensions,
	val brand: String
) : Parcelable