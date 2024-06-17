package com.pfv.cryptotracker.data.dvo

import com.pfv.cryptotracker.constants.TransactionCategory
import com.pfv.cryptotracker.constants.TransactionType
import java.util.Date

data class TransactionDvo(
    val category: TransactionCategory,
    val date: Date,
    val cryptoInfo: CryptoInfoDvo,
    val transactionType: TransactionType
)
