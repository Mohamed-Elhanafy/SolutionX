package com.example.solutionx.features.authentication.data.model.dto


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class CountryDto(
    @SerializedName("code")
    val code: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone_code")
    val phoneCode: String
) : Parcelable