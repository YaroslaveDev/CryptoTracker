package com.pfv.cryptotracker.ui.screens.wallet_info_screen.screen_state

sealed class WalletInfoScreenState {

    object SetupState : WalletInfoScreenState()
    object EmptyState : WalletInfoScreenState()
    object SuccessState : WalletInfoScreenState()
}