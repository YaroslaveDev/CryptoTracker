package com.pfv.cryptotracker.ui.ext

import android.content.Context
import com.pfv.cryptotracker.constants.CryptoTypes

fun Double.formCryptoCurrentStateString(context: Context, cryptoType: CryptoTypes) =
    context.getString(cryptoType.getStringRes()) + " " + this