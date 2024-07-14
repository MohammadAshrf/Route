package com.example.route.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meta(
	val createdAt: String,
	val qrCode: String,
	val barcode: String,
	val updatedAt: String
) : Parcelable