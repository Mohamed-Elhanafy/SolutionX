package com.example.solutionx.features.authentication.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    val accessToken: String,
    val user: User
) : Parcelable