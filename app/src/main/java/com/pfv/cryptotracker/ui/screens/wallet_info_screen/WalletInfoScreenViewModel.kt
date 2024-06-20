package com.pfv.cryptotracker.ui.screens.wallet_info_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pfv.cryptotracker.constants.CryptoTypes
import com.pfv.cryptotracker.constants.TransactionCategory
import com.pfv.cryptotracker.constants.TransactionType
import com.pfv.cryptotracker.data.dvo.CryptoInfoDvo
import com.pfv.cryptotracker.data.dvo.CurrentBitcoinStateDvo
import com.pfv.cryptotracker.data.dvo.TransactionDvo
import com.pfv.cryptotracker.data.dvo.WalletBalanceDvo
import com.pfv.cryptotracker.data.local.entity.TransactionEntity
import com.pfv.cryptotracker.domain.ResultState
import com.pfv.cryptotracker.domain.use_case.GetBitcoinStateUseCase
import com.pfv.cryptotracker.domain.use_case.TransactionsOperationsUseCase
import com.pfv.cryptotracker.domain.use_case.WalletBalanceOperationsUseCase
import com.pfv.cryptotracker.ui.screens.create_transaction_screen.nav_state.CreateTransactionNavState
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.data_state.WalletInfoDataState
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.event.WalletScreenEvent
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.form.WalletInfoScreenForm
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.nav_state.WalletInfoScreenNavState
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.screen_state.WalletInfoScreenState
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.ui_state.WalletInfoScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class WalletInfoScreenViewModel @Inject constructor(
    private val getBitcoinStateUseCase: GetBitcoinStateUseCase,
    private val walletBalanceOperationsUseCase: WalletBalanceOperationsUseCase,
    private val transactionsOperationsUseCase: TransactionsOperationsUseCase
) : ViewModel() {

    var screeState by mutableStateOf<WalletInfoScreenState>(WalletInfoScreenState.SetupState)
    var uiState by mutableStateOf<WalletInfoScreenUiState>(WalletInfoScreenUiState.InitState)
    var navState by mutableStateOf<WalletInfoScreenNavState>(WalletInfoScreenNavState.InitState)
    var form by mutableStateOf(WalletInfoScreenForm())
    var dataState by mutableStateOf(WalletInfoDataState())

    val transactions: Flow<PagingData<TransactionEntity>> = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { transactionsOperationsUseCase.getAllTransactions() }
    ).flow.cachedIn(viewModelScope)

    fun reduceEvent(event: WalletScreenEvent){

        when(event){
            WalletScreenEvent.SetDeposit -> updateUiState(WalletInfoScreenUiState.MakeDepositPopup)
            WalletScreenEvent.StartTransaction -> updateNavState(WalletInfoScreenNavState.NavToMakeTransactionScreen)
            WalletScreenEvent.OnSetNewDepositValue -> {
                makeTransaction()
            }
            is WalletScreenEvent.UpdateDepositValue -> updateDepositValue(event.value)
        }
    }

    fun getBitcoinState(){

        viewModelScope.launch {

            getBitcoinStateUseCase().collect {

                when(it){
                    is ResultState.Error -> {
                        updateUiState(
                            WalletInfoScreenUiState.Error(it.errorDvo.errorMessage.orEmpty())
                        )
                    }
                    is ResultState.Success -> {

                        val data = it.data as CurrentBitcoinStateDvo

                        dataState = dataState.copy(
                            currentBitcoinState = data.cryptoCurrenciesDvo.usd.rateFloat.toDouble(),
                            currentBitcoinStateString = data.cryptoCurrenciesDvo.usd.rate
                        )
                    }
                }
            }
        }
    }

    fun getWalletBalance(){

        viewModelScope.launch {

            walletBalanceOperationsUseCase.getWalletBalance().collect {

                when(it){
                    is ResultState.Success -> {

                        val data = it.data as WalletBalanceDvo

                        dataState = dataState.copy(
                            walletBalance = data.value
                        )
                    }
                    is ResultState.Error -> {
                        updateUiState(
                            WalletInfoScreenUiState.Error(it.errorDvo.errorMessage.orEmpty())
                        )
                    }
                }
            }
        }
    }

    private fun makeTransaction() {

        viewModelScope.launch {

            transactionsOperationsUseCase.makeTransaction(
                transaction = TransactionDvo(
                    category = TransactionCategory.OTHER,
                    date = Calendar.getInstance().time,
                    cryptoInfo = CryptoInfoDvo(
                        type = CryptoTypes.BTC,
                        value = form.depositInputValue.toDouble()
                    ),
                    transactionType = TransactionType.DEPOSIT
                )
            )

            setNewDepositValue()

        }
    }

    private fun setNewDepositValue() {

        viewModelScope.launch {

            walletBalanceOperationsUseCase.updateWalletBalance(
                walletBalanceDvo = WalletBalanceDvo(
                    value = form.depositInputValue.toDouble() + dataState.walletBalance
                )
            )

            clearForm()
        }
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

    fun processScreenState(transactions: Int){
        if (transactions == 0){
            updateScreenState(WalletInfoScreenState.EmptyState)
        }else{
            updateScreenState(WalletInfoScreenState.SuccessState)
        }
    }

    fun resetUiState() = updateUiState(WalletInfoScreenUiState.InitState)

    fun resetNavState() = updateNavState(WalletInfoScreenNavState.InitState)
}