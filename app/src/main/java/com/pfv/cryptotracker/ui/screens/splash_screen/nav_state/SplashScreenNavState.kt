package com.pfv.cryptotracker.ui.screens.splash_screen.nav_state

sealed class SplashScreenNavState {

    object InitState : SplashScreenNavState()
    object NavToWalletInfoScreen : SplashScreenNavState()
}