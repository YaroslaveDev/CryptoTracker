package com.pfv.cryptotracker.ui.screens.splash_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pfv.cryptotracker.R
import com.pfv.cryptotracker.data.constants.GlobalAppConstants
import com.pfv.cryptotracker.ui.navigation.Screen
import com.pfv.cryptotracker.ui.screens.splash_screen.event.SplashScreenEvent
import com.pfv.cryptotracker.ui.screens.splash_screen.nav_state.SplashScreenNavState
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashScreenViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit){
        delay(GlobalAppConstants.SPLASH_DURATION)
        viewModel.reduceEvent(SplashScreenEvent.NavToWalletInfoScreen)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){

            Image(
                modifier = Modifier
                    .size(200.dp),
                painter = painterResource(id = R.drawable.ic_bitcoin),
                contentDescription = "img"
            )
        }
    }

    LaunchedEffect(viewModel.navState){

        when(val state = viewModel.navState){
            SplashScreenNavState.InitState -> {}
            SplashScreenNavState.NavToWalletInfoScreen -> {
                navController.navigate(Screen.WalletInfoScreen){
                    navController.popBackStack()
                }
            }
        }
    }
}