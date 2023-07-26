package com.erzhanov.coreapp.data.auth.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SignInRequest(
    @SerialName("login") val login: String,
    @SerialName("password") val password: String,
)