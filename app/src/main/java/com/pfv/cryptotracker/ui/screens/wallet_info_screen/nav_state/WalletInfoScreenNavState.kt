package com.pfv.cryptotracker.ui.screens.wallet_info_screen.nav_state

sealed class WalletInfoScreenNavState {

    object InitState : WalletInfoScreenNavState()
    object NavToMakeTransactionScreen : WalletInfoScreenNavState()
}