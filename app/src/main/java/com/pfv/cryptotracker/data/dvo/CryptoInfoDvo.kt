package com.pfv.cryptotracker.data.dvo

import com.pfv.cryptotracker.constants.CryptoTypes
import com.pfv.cryptotracker.domain.NetworkEntity

data class CryptoInfoDvo(
    val type: CryptoTypes,
    val value: Double
) : NetworkEntity
