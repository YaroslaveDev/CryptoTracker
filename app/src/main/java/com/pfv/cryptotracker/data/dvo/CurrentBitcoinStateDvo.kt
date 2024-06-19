package com.pfv.cryptotracker.data.dvo

import com.pfv.cryptotracker.domain.NetworkEntity

data class CurrentBitcoinStateDvo(
    val cryptoCurrenciesDvo: CryptoCurrenciesDvo
) : NetworkEntity
