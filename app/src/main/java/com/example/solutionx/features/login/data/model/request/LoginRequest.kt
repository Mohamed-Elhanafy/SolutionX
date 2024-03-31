package com.example.solutionx.features.login.data.model.request

import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone
import com.google.gson.annotations.SerializedName


data class LoginRequest(
    @SerializedName("phone")
    val phone: PhoneRequest,

    @SerializedName("password")
    val password: String
)