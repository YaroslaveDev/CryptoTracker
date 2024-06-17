package com.pfv.cryptotracker.ui.screens.wallet_info_screen.event

sealed class WalletScreenEvent {

    object SetDeposit : WalletScreenEvent()
    object StartTransaction : WalletScreenEvent()
}