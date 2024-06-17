package com.pfv.cryptotracker.data.dvo

import com.pfv.cryptotracker.constants.CryptoTypes

data class CryptoInfoDvo(
    val type: CryptoTypes,
    val value: Double
)
