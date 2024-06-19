package com.pfv.cryptotracker.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class CryptoCurrencyDto(
    @SerializedName("code")
    val code: String?,
    @SerializedName("rate")
    val rate: String?,
    @SerializedName("rate_float")
    val rateFloat: Float?
)
