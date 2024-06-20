package com.pfv.cryptotracker.data.remote.api

import com.pfv.cryptotracker.data.dto.CurrentBitcoinStateDto
import com.pfv.cryptotracker.data.remote.network.NetworkResult
import com.pfv.cryptotracker.domain.ResultState
import retrofit2.http.GET

interface BitcoinStateService {

    @GET(CURRENT_BITCOIN_STATE)
    suspend fun getCurrentBitcoinState() : NetworkResult<CurrentBitcoinStateDto>

    companion object {

        const val CURRENT_BITCOIN_STATE = "v1/bpi/currentprice.json"
    }
}