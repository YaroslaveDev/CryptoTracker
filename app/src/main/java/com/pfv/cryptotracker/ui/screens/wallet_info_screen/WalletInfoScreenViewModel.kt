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
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.form.WalletInfoScreenForm
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.nav_state.WalletInfoScreenNavState
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.screen_state.WalletInfoScreenState
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.ui_state.WalletInfoScreenUiState
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
    var uiState by mutableStateOf<WalletInfoScreenUiState>(WalletInfoScreenUiState.InitState)
    var navState by mutableStateOf<WalletInfoScreenNavState>(WalletInfoScreenNavState.InitState)
    var form by mutableStateOf(WalletInfoScreenForm())
    var walletBalance by mutableStateOf(0.0)

    fun reduceEvent(event: WalletScreenEvent){

        when(event){
            WalletScreenEvent.SetDeposit -> updateUiState(WalletInfoScreenUiState.MakeDepositPopup)
            WalletScreenEvent.StartTransaction -> {}
            WalletScreenEvent.OnSetNewDepositValue -> {

            }
            is WalletScreenEvent.UpdateDepositValue -> updateDepositValue(event.value)
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

    private fun setNewDepositValue() {

    }

    private fun updateDepositValue(value: String){
        form = form.copy(
            depositInputValue = value
        )
    }

    private fun updateUiState(state: WalletInfoScreenUiState){
        uiState = state
    }

    private fun updateNavState(state: WalletInfoScreenNavState){
        navState = state
    }

    private fun updateScreenState(state: WalletInfoScreenState){
        screeState= state
    }

    fun clearForm(){
        form = form.copy(
            depositInputValue = ""
        )
    }

    fun resetUiState() = updateUiState(WalletInfoScreenUiState.InitState)

    fun resetNavState() = updateNavState(WalletInfoScreenNavState.InitState)
}