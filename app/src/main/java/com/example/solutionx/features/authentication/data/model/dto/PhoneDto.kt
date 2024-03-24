package com.example.solutionx.features.authentication.data.model.dto


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class PhoneDto(
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("extension")
    val extension: String,
    @SerializedName("holder_name")
    val holderName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("number")
    val number: String,
    @SerializedName("type")
    val type: String
) : Parcelable