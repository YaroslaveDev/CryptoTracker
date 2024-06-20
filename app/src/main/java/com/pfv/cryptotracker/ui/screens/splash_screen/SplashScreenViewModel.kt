package com.pfv.cryptotracker.ui.screens.splash_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pfv.cryptotracker.ui.screens.splash_screen.event.SplashScreenEvent
import com.pfv.cryptotracker.ui.screens.splash_screen.nav_state.SplashScreenNavState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor() : ViewModel() {

    var navState by mutableStateOf<SplashScreenNavState>(SplashScreenNavState.InitState)

    fun reduceEvent(event: SplashScreenEvent){

        when(event){
            SplashScreenEvent.NavToWalletInfoScreen -> updateNavState(SplashScreenNavState.NavToWalletInfoScreen)
        }
    }

    private fun updateNavState(state: SplashScreenNavState){
        navState = state
    }
}