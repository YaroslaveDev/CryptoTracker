package com.pfv.cryptotracker.data.dvo

import com.pfv.cryptotracker.data.constants.GlobalAppConstants.EMPTY_STRING

data class ErrorDvo(
    val errorRes: Int? = null,
    val errorMessage: String? = null,
    val errorCode: String? = null
) {
    override fun toString(): String {
        return when {
            errorMessage != null -> errorMessage
            errorRes != null -> errorRes.toString()
            else -> EMPTY_STRING
        }
    }
}