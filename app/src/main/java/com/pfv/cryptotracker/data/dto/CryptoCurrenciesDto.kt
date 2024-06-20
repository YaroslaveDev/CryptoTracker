package com.pfv.cryptotracker.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class CryptoCurrenciesDto(
    @SerializedName("USD")
    val usd: CryptoCurrencyDto?
)
