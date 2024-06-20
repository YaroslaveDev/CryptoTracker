package com.pfv.cryptotracker.ui.navigation

import kotlinx.serialization.Serializable

object NavConstants {

    const val SplashScreenRoute = "splash_screen_route"
    const val WalletInfoScreen = "wallet_info_screen_route"
    const val CreateTransactionScreen = "create_transaction_screen_route"
}

sealed interface Screen {
    @Serializable
    data object Splash : Screen

    @Serializable
    data object WalletInfoScreen : Screen

    @Serializable
    data class CreateTransactionScreen(val walletBalance: String) : Screen

}