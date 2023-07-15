package com.erzhanov.coreapp.data.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Units {
    @SerialName("metric") METRIC,
    @SerialName("imperial") IMPERIAL,
}