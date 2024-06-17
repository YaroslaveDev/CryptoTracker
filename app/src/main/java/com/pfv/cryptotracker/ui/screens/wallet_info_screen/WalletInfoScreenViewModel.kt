package com.pfv.cryptotracker.ui.screens.wallet_info_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.event.WalletScreenEvent
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.screen_state.WalletInfoScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WalletInfoScreenViewModel @Inject constructor() : ViewModel() {

    var screeState by mutableStateOf<WalletInfoScreenState>(WalletInfoScreenState.EmptyState)

    fun reduceEvent(event: WalletScreenEvent){

        when(event){
            WalletScreenEvent.SetDeposit -> {}
            WalletScreenEvent.StartTransaction -> {}
        }
    }
}