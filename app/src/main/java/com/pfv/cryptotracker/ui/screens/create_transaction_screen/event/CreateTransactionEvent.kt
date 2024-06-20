package com.pfv.cryptotracker.ui.screens.create_transaction_screen.event

import com.pfv.cryptotracker.constants.TransactionCategory

sealed class CreateTransactionEvent {

    object NavigateBack : CreateTransactionEvent()
    object MakeTransaction : CreateTransactionEvent()
    data class UpdateTransactionValue(val value: String) : CreateTransactionEvent()
    data class UpdateCategory(val category: TransactionCategory) : CreateTransactionEvent()
}