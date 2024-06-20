package com.pfv.cryptotracker.ui.screens.wallet_info_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pfv.cryptotracker.R

@Composable
fun WalletInfoEmptyState(
    modifier: Modifier
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){

        Text(
            text = stringResource(id = R.string.wallet_info_empty_state),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}