package com.pfv.cryptotracker.ui.screens.wallet_info_screen.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pfv.cryptotracker.R
import com.pfv.cryptotracker.data.dvo.CryptoInfoDvo
import com.pfv.cryptotracker.ui.ext.formCryptoCurrentStateString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletTopBar(
    context: Context,
    cryptoInfo: CryptoInfoDvo
) {

    TopAppBar(
        modifier = Modifier,
        title = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp
                    ),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = cryptoInfo.value.formCryptoCurrentStateString(context, cryptoInfo.type),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    )
}