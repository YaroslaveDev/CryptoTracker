package com.pfv.cryptotracker.ui.screens.wallet_info_screen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.pfv.cryptotracker.R

@Composable
fun MakeDepositPopup(
    isValueValid: Boolean,
    depositValue: String,
    onDepositValueUpdate: (String) -> Unit,
    onMakeDeposit : () -> Unit,
    onDismiss : () -> Unit
) {

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = onMakeDeposit,
                enabled = isValueValid
            ) {
                Text(
                    text = stringResource(id = R.string.make_deposit),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        title = {
            Text(
                text = stringResource(id = R.string.make_new_deposit),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium
            )
        },
        text = {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                value = depositValue,
                onValueChange = onDepositValueUpdate
            )
        }
    )
}