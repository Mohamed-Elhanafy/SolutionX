package com.example.solutionx.features.login.data.model.dto


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
 data class UserDto(
    @SerializedName("all_permissions")
    val allPermissions: List<String?>? = null,
    @SerializedName("birthdate")
    val birthdate: String? = null,
    @SerializedName("blocked")
    val blocked: Int? = null,
    @SerializedName("country")
    val countryDto: CountryDto? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("email_verified")
    val emailVerified: Boolean? = null,
    @SerializedName("firstname")
    val firstname: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("lastname")
    val lastname: String? = null,
    @SerializedName("middlename")
    val middlename: String? = null,
    @SerializedName("phone")
    val phoneDto: PhoneDto? = null,
    @SerializedName("phone_verified")
    val phoneVerified: Boolean? = null,
    @SerializedName("username")
    val username: String? = null
) : Parcelable