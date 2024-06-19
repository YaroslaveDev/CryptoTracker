package com.pfv.cryptotracker.ui.screens.wallet_info_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pfv.cryptotracker.constants.CryptoTypes
import com.pfv.cryptotracker.data.dvo.CryptoInfoDvo
import com.pfv.cryptotracker.ui.base.popups.NoInternetConnectionDialog
import com.pfv.cryptotracker.ui.base.setup.BaseAppSetupAnimation
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.components.MakeDepositPopup
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.components.WalletActionsSection
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.components.WalletInfoEmptyState
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.components.WalletItem
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.components.WalletTopBar
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.event.WalletScreenEvent
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.nav_state.WalletInfoScreenNavState
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.screen_state.WalletInfoScreenState
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.ui_state.WalletInfoScreenUiState

@Composable
fun WalletInfoScreen(
    navController: NavController,
    viewModel: WalletInfoScreenViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            WalletTopBar(
                context = context,
                cryptoInfo = CryptoInfoDvo(
                    type = CryptoTypes.BTC,
                    value = 60.1959121
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
                currentBalance = 0.5555111
            )

            WalletActionsSection(
                modifier = Modifier
                    .fillMaxWidth(),
                onEvent = viewModel::reduceEvent
            )

            when(viewModel.screeState){
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

                }
            }
        }

        when(val state = viewModel.uiState){
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

    LaunchedEffect(viewModel.navState){

        when(val state = viewModel.navState){
            WalletInfoScreenNavState.InitState -> {}
            WalletInfoScreenNavState.NavToMakeTransactionScreen -> {

                viewModel.resetNavState()
            }
        }
    }
}