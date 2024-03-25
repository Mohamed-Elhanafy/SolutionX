package com.example.solutionx.features.authentication.data.model.dto


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class CountryDto(
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("flag")
    val flag: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("phone_code")
    val phoneCode: String? = null
) : Parcelable