package com.pfv.cryptotracker.ui.screens.create_transaction_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pfv.cryptotracker.constants.CryptoTypes
import com.pfv.cryptotracker.constants.TransactionCategory
import com.pfv.cryptotracker.constants.TransactionType
import com.pfv.cryptotracker.data.dvo.CryptoInfoDvo
import com.pfv.cryptotracker.data.dvo.TransactionDvo
import com.pfv.cryptotracker.data.dvo.TransactionsDvo
import com.pfv.cryptotracker.data.dvo.WalletBalanceDvo
import com.pfv.cryptotracker.domain.use_case.TransactionsOperationsUseCase
import com.pfv.cryptotracker.domain.use_case.WalletBalanceOperationsUseCase
import com.pfv.cryptotracker.ui.screens.create_transaction_screen.event.CreateTransactionEvent
import com.pfv.cryptotracker.ui.screens.create_transaction_screen.form.CreateTransactionForm
import com.pfv.cryptotracker.ui.screens.create_transaction_screen.nav_state.CreateTransactionNavState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CreateTransactionScreenViewModel @Inject constructor(
    private val transactionsOperationsUseCase: TransactionsOperationsUseCase,
    private val walletBalanceOperationsUseCase: WalletBalanceOperationsUseCase,
) : ViewModel() {

    var form by mutableStateOf(CreateTransactionForm())
    var navState by mutableStateOf<CreateTransactionNavState>(CreateTransactionNavState.InitState)
    var walletBalance by mutableStateOf(0.0)

    fun reduceEvent(event: CreateTransactionEvent){

        when(event){
            CreateTransactionEvent.MakeTransaction -> makeTransaction()
            CreateTransactionEvent.NavigateBack -> {}
            is CreateTransactionEvent.UpdateTransactionValue -> {
                form = form.copy(
                    transactionValue = event.value
                )
            }

            is CreateTransactionEvent.UpdateCategory -> {
                form = form.copy(
                    selectedCategory = event.category
                )
            }
        }
    }


    private fun makeTransaction() {

        viewModelScope.launch {

            transactionsOperationsUseCase.makeTransaction(
                transaction = TransactionDvo(
                    category = form.selectedCategory ?: TransactionCategory.OTHER,
                    date = Calendar.getInstance().time,
                    cryptoInfo = CryptoInfoDvo(
                        type = CryptoTypes.BTC,
                        value = form.transactionValue.toDouble()
                    ),
                    transactionType = TransactionType.SPEND
                )
            )

            setNewDepositValue()

            updateNavState(CreateTransactionNavState.NavigateBack)
        }
    }

    private fun setNewDepositValue() {

        viewModelScope.launch {

            walletBalanceOperationsUseCase.updateWalletBalance(
                walletBalanceDvo = WalletBalanceDvo(
                    value = walletBalance - form.transactionValue.toDouble()
                )
            )
        }
    }

    fun updateWalletBalance(value: Double){
        walletBalance = value
    }

    fun updateNavState(state: CreateTransactionNavState){

        navState = state
    }
}