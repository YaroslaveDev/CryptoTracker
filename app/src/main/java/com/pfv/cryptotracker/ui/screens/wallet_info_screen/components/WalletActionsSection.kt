package com.pfv.cryptotracker.ui.screens.wallet_info_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pfv.cryptotracker.R
import com.pfv.cryptotracker.ui.base.btn.BaseAppButton
import com.pfv.cryptotracker.ui.screens.wallet_info_screen.event.WalletScreenEvent

@Composable
fun WalletActionsSection(
    modifier: Modifier,
    onEvent: (WalletScreenEvent) -> Unit
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        BaseAppButton(
            modifier = Modifier
                .weight(1f),
            text = stringResource(id = R.string.deposit),
            onClick = {
                onEvent(WalletScreenEvent.SetDeposit)
            }
        )

        Spacer(modifier = Modifier.width(30.dp))

        BaseAppButton(
            modifier = Modifier
                .weight(1f),
            text = stringResource(id = R.string.add_transaction),
            onClick = {
                onEvent(WalletScreenEvent.StartTransaction)
            }
        )
    }
}