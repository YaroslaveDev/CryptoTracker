package com.pfv.cryptotracker.constants

import com.pfv.cryptotracker.R

enum class CryptoTypes {

    BTC;

    fun getStringRes() =
        when(this){
            BTC -> R.string.btc
        }

}