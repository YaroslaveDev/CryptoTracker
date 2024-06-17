package com.pfv.cryptotracker.ui.screens.wallet_info_screen.components

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pfv.cryptotracker.ui.ext.btcToStringValue

@Composable
fun WalletItem(
    modifier: Modifier,
    context: Context,
    currentBalance: Double
) {

    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){

        Text(
            modifier = Modifier
                .padding(
                    vertical = 70.dp
                ),
            text = currentBalance.btcToStringValue(context),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview
@Composable
fun WalletItem_Preview(){


}