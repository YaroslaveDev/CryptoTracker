package com.pfv.cryptotracker.data.dto

import kotlinx.serialization.SerialName

data class CryptoCurrenciesDto(
    @SerialName("USD")
    val usd: CryptoCurrencyDto?
)
