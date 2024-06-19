package com.pfv.cryptotracker.data.dto

import com.pfv.cryptotracker.domain.NetworkEntity

data class CurrentBitcoinStateDto(
    val bitcoinCurrencies : CryptoCurrenciesDto?
) : NetworkEntity
