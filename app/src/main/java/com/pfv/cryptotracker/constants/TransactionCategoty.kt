package com.pfv.cryptotracker.constants

import com.pfv.cryptotracker.R

enum class TransactionCategory {

    GROCERIES,
    TAXI,
    ELECTRONICS,
    RESTAURANT,
    OTHER;

    fun getStringRes() =
        when(this) {
            GROCERIES -> R.string.groceries
            TAXI -> R.string.taxi
            ELECTRONICS -> R.string.electronics
            RESTAURANT -> R.string.restaurant
            OTHER -> R.string.other
        }
}