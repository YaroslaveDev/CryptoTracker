package com.pfv.cryptotracker.data.dvo

import kotlinx.serialization.SerialName

data class CryptoCurrencyDvo(
    val code: String,
    val rate: String,
    val rateFloat: Float
)
