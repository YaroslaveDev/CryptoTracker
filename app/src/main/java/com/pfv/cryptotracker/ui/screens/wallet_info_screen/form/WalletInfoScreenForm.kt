package com.pfv.cryptotracker.ui.screens.wallet_info_screen.form

import com.pfv.cryptotracker.data.dvo.CurrentBitcoinStateDvo
import com.pfv.cryptotracker.ui.ext.isNotNull

data class WalletInfoScreenForm(
    val depositInputValue: String = "",
) {

    fun isDepositValueValid() =
        depositInputValue.isNotBlank() &&
                depositInputValue.toDoubleOrNull().isNotNull() &&
                (depositInputValue.toDoubleOrNull() ?: 0.0) > 0.0

}
