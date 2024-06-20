package com.pfv.cryptotracker.ui.screens.create_transaction_screen.nav_state

sealed class CreateTransactionNavState {

    object InitState : CreateTransactionNavState()
    object NavigateBack : CreateTransactionNavState()
}