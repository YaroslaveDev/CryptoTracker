package com.pfv.cryptotracker.ui.screens.create_transaction_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pfv.cryptotracker.R
import com.pfv.cryptotracker.constants.TransactionCategory
import com.pfv.cryptotracker.ui.screens.create_transaction_screen.event.CreateTransactionEvent
import com.pfv.cryptotracker.ui.screens.create_transaction_screen.nav_state.CreateTransactionNavState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateTransactionScreen(
    walletBalance: Double,
    navController: NavController,
    viewModel: CreateTransactionScreenViewModel = hiltViewModel()
) {

    val textColor = MaterialTheme.colorScheme.onSurface
    val selectedTextColor = MaterialTheme.colorScheme.error

    LaunchedEffect(Unit){
        viewModel.updateWalletBalance(walletBalance)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    value = viewModel.form.transactionValue,
                    onValueChange = {
                        viewModel.reduceEvent(CreateTransactionEvent.UpdateTransactionValue(it))
                    }
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    items(TransactionCategory.entries.toTypedArray()) {

                        val categoryColor by remember {
                            derivedStateOf {
                                if (it == viewModel.form.selectedCategory) {
                                    selectedTextColor
                                }else{
                                    textColor
                                }
                            }
                        }

                        TextButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            onClick = {
                                viewModel.reduceEvent(CreateTransactionEvent.UpdateCategory(it))
                            }
                        ) {
                            Text(
                                text = stringResource(id = it.getStringRes()),
                                color = categoryColor,
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }

                Button(
                    onClick = {
                        viewModel.reduceEvent(CreateTransactionEvent.MakeTransaction)
                    },
                    enabled = viewModel.form.isTransactionValueValid()
                ) {
                    Text(
                        text = stringResource(id = R.string.add),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        }
    }

    LaunchedEffect(viewModel.navState){

        when(viewModel.navState){
            CreateTransactionNavState.InitState -> {}
            CreateTransactionNavState.NavigateBack -> {
                navController.navigateUp()
            }
        }
    }
}