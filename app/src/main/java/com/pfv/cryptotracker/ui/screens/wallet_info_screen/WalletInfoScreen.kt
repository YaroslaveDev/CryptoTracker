package com.pfv.cryptotracker.ui.screens.wallet_info_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.pfv.cryptotracker.constants.CryptoTypes
import com.pfv.cryptotracker.constants.TransactionType
import com.pfv.cryptotracker.data.dvo.CryptoInfoDvo
import com.pfv.cryptotracker.ui.base.popups.NoInternetConnectionDialog
import com.pfv.cryptotracker.ui.base.setup.BaseAppSetupAnimation
import com.pfv.cryptotracker.ui.navigation.Screen
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.components.MakeDepositPopup
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.components.WalletActionsSection
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.components.WalletInfoEmptyState
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.components.WalletItem
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.components.WalletTopBar
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.event.WalletScreenEvent
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.nav_state.WalletInfoScreenNavState
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.screen_state.WalletInfoScreenState
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.ui_state.WalletInfoScreenUiState
import io.ktor.websocket.Frame

@Composable
fun WalletInfoScreen(
    navController: NavController,
    viewModel: WalletInfoScreenViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val transactions = viewModel.transactions.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {

        viewModel.getBitcoinState()
        viewModel.getWalletBalance()
    }

    LaunchedEffect(transactions.itemCount){
        viewModel.processScreenState(transactions = transactions.itemCount)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            WalletTopBar(
                context = context,
                cryptoInfo = CryptoInfoDvo(
                    type = CryptoTypes.BTC,
                    value = viewModel.dataState.currentBitcoinState
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = paddingValues.calculateTopPadding())
                .padding(horizontal = 20.dp)
        ) {
            WalletItem(
                modifier = Modifier
                    .padding(top = 20.dp),
                context = context,
                currentBalance = viewModel.dataState.walletBalance
            )

            WalletActionsSection(
                modifier = Modifier
                    .fillMaxWidth(),
                onEvent = viewModel::reduceEvent
            )

            when (viewModel.screeState) {
                WalletInfoScreenState.EmptyState -> {
                    WalletInfoEmptyState(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    )
                }

                WalletInfoScreenState.SetupState -> {
                    BaseAppSetupAnimation(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    )
                }

                WalletInfoScreenState.SuccessState -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(30.dp),
                        contentPadding = PaddingValues(vertical = 40.dp)
                    ) {

                        items(transactions.itemCount){

                            val item = transactions[it]

                            Text(
                                text = "${item?.category}   -   ${item?.amount}  -  ${item?.createdAt} - " +
                                        if (item?.transactionType == TransactionType.DEPOSIT) "(DEPOSIT)" else "(SPEND)",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        transactions.apply {
                            when {
                                loadState.refresh is LoadState.Loading -> {
                                    item { CircularProgressIndicator() }
                                }
                                loadState.append is LoadState.Loading -> {
                                    item { CircularProgressIndicator() }
                                }
                            }
                        }
                    }
                }
            }
        }

        when (val state = viewModel.uiState) {
            is WalletInfoScreenUiState.Error -> {}
            WalletInfoScreenUiState.InitState -> {}
            WalletInfoScreenUiState.MakeDepositPopup -> {
                MakeDepositPopup(
                    isValueValid = viewModel.form.isDepositValueValid(),
                    depositValue = viewModel.form.depositInputValue,
                    onDepositValueUpdate = {
                        viewModel.reduceEvent(WalletScreenEvent.UpdateDepositValue(it))
                    },
                    onMakeDeposit = {
                        viewModel.reduceEvent(WalletScreenEvent.OnSetNewDepositValue)
                        viewModel.resetUiState()
                    },
                    onDismiss = {
                        viewModel.resetUiState()
                        viewModel.clearForm()
                    }
                )
            }

            WalletInfoScreenUiState.NoInternetConnection -> {
                NoInternetConnectionDialog(
                    onClose = {
                        viewModel.resetUiState()
                    }
                )
            }

            WalletInfoScreenUiState.SetupState -> {
                BaseAppSetupAnimation(modifier = Modifier.fillMaxSize())
            }
        }
    }

    LaunchedEffect(viewModel.navState) {

        when (val state = viewModel.navState) {
            WalletInfoScreenNavState.InitState -> {}
            WalletInfoScreenNavState.NavToMakeTransactionScreen -> {
                navController.navigate(Screen.CreateTransactionScreen(walletBalance = viewModel.dataState.walletBalance.toString()))
                viewModel.resetNavState()
            }
        }
    }
}