package com.pfv.cryptotracker.data.dto

import kotlinx.serialization.SerialName

data class CryptoCurrencyDto(
    @SerialName("code")
    val code: String?,
    @SerialName("rate")
    val rate: String?,
    @SerialName("rate_float")
    val rateFloat: Float?
)
