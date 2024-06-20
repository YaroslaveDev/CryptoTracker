package com.pfv.cryptotracker.ui.ext

import android.content.Context
import com.pfv.cryptotracker.R

fun Double.btcToStringValue(context: Context) =
    "$this " + context.getString(R.string.btc)
