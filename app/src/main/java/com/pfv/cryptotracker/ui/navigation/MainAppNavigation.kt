package com.pfv.cryptotracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pfv.cryptotracker.ui.screens.create_transaction_screen.CreateTransactionScreen
import com.pfv.cryptotracker.ui.screens.splash_screen.SplashScreen
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.WalletInfoScreen

@Composable
fun MainAppNavigation(
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = Screen.Splash
    ) {

        composable<Screen.Splash> {
            SplashScreen(navController = navHostController)
        }

        composable<Screen.WalletInfoScreen> {
            WalletInfoScreen(navController = navHostController)
        }

        composable<Screen.CreateTransactionScreen> {
            CreateTransactionScreen(navController = navHostController)
        }
    }
}