package com.example.route.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Reviews(
	val date: String,
	val reviewerName: String,
	val reviewerEmail: String,
	val rating: Int,
	val comment: String
) : Parcelable