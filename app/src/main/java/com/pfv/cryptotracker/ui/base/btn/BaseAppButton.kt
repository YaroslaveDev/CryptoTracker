package com.pfv.cryptotracker.ui.base.btn

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BaseAppButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {

    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 10.dp),
            text = text,
            color = MaterialTheme.colorScheme.surface,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}