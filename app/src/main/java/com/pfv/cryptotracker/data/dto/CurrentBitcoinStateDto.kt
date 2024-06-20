package com.pfv.cryptotracker.data.dto

import com.google.gson.annotations.SerializedName
import com.pfv.cryptotracker.domain.NetworkEntity
import kotlinx.serialization.SerialName

data class CurrentBitcoinStateDto(
    @SerializedName("bpi")
    val bitcoinCurrencies : CryptoCurrenciesDto?
) : NetworkEntity
