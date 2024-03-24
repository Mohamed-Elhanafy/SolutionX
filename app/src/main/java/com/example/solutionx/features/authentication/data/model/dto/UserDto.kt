package com.example.solutionx.features.authentication.data.model.dto


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class UserDto(
    @SerializedName("all_permissions")
    val allpermissions: List<String>,
    @SerializedName("birthdate")
    val birthdate: String,
    @SerializedName("blocked")
    val blocked: Int,
    @SerializedName("country")
    val countryDto: CountryDto,
    @SerializedName("email")
    val email: String,
    @SerializedName("email_verified")
    val emailVerified: Boolean,
    @SerializedName("firstname")
    val firstname: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("middlename")
    val middlename: String,
    @SerializedName("phone")
    val phoneDto: PhoneDto,
    @SerializedName("phone_verified")
    val phoneVerified: Boolean,
    @SerializedName("username")
    val username: String
) : Parcelable