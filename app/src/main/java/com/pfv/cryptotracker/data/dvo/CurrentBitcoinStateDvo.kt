package com.pfv.cryptotracker.data.dvo

import com.pfv.cryptotracker.domain.NetworkEntity
import java.util.Date

data class CurrentBitcoinStateDvo(
    val cryptoCurrenciesDvo: CryptoCurrenciesDvo,
    val updatedAt: Date
) : NetworkEntity
