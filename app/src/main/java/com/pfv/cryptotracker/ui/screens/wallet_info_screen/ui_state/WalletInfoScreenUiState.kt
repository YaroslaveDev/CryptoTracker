package com.pfv.cryptotracker.ui.screens.wallet_info_screen.ui_state

sealed class WalletInfoScreenUiState {

    object InitState : WalletInfoScreenUiState()
    object NoInternetConnection : WalletInfoScreenUiState()
    data class Error(val text: String) : WalletInfoScreenUiState()
    object SetupState : WalletInfoScreenUiState()
    object MakeDepositPopup : WalletInfoScreenUiState()
}