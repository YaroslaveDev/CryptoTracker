package com.pfv.cryptotracker.ui.base.popups

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.pfv.cryptotracker.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoInternetConnectionDialog(
    onClose: () -> Unit
) {

    AlertDialog(
        title = {
            Text(
                text = stringResource(id = R.string.error),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium
            )
        },
        onDismissRequest = onClose,
        confirmButton = {
            TextButton(onClick = onClose) {
                Text(
                    text = stringResource(id = R.string.ok),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        text = {
            Text(
                text = stringResource(id = R.string.no_internet_connection),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleSmall
            )
        }
    )
}