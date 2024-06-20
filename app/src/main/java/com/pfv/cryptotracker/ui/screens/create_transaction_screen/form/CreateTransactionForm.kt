package com.pfv.cryptotracker.ui.screens.create_transaction_screen.form

import com.pfv.cryptotracker.constants.TransactionCategory
import com.pfv.cryptotracker.ui.ext.isNotNull

data class CreateTransactionForm(
    val selectedCategory: TransactionCategory? = null,
    val transactionValue: String = ""
){

    fun isTransactionValueValid() =
        transactionValue.isNotBlank() &&
                selectedCategory.isNotNull() &&
                transactionValue.toDoubleOrNull().isNotNull() &&
                (transactionValue.toDoubleOrNull() ?: 0.0) > 0.0
}
