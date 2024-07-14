package com.example.route.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dimensions(
	val depth: Double,
	val width: Double,
	val height: Double
) : Parcelable