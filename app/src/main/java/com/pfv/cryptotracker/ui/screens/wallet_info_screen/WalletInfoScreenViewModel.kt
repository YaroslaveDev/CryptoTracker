package com.pfv.cryptotracker.ui.screens.wallet_info_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pfv.cryptotracker.domain.ResultState
import com.pfv.cryptotracker.domain.use_case.GetBitcoinStateUseCase
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.event.WalletScreenEvent
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.screen_state.WalletInfoScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletInfoScreenViewModel @Inject constructor(
    private val getBitcoinStateUseCase: GetBitcoinStateUseCase
) : ViewModel() {

    var screeState by mutableStateOf<WalletInfoScreenState>(WalletInfoScreenState.EmptyState)

    init {

    }

    fun reduceEvent(event: WalletScreenEvent){

        when(event){
            WalletScreenEvent.SetDeposit -> {
                getBitcoinState()
            }
            WalletScreenEvent.StartTransaction -> {}
        }
    }

    private fun getBitcoinState(){

        viewModelScope.launch {

            getBitcoinStateUseCase().collect {

                when(it){
                    is ResultState.Error -> {
                        Log.i("dddddd", it.errorDvo.errorMessage.toString())
                    }
                    is ResultState.Success -> {
                        Log.i("dddddd", it.data.toString())
                    }
                }
            }
        }
    }
}