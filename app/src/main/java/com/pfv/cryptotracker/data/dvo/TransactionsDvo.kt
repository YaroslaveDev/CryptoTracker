package com.pfv.cryptotracker.data.dvo

import com.pfv.cryptotracker.domain.NetworkEntity

data class TransactionsDvo(
    val transactions: List<TransactionDvo>
) : NetworkEntity