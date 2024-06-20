package com.pfv.cryptotracker.domain.repository

import com.pfv.cryptotracker.data.local.entity.BitcoinStateEntity
import com.pfv.cryptotracker.domain.NetworkEntity
import com.pfv.cryptotracker.domain.ResultState
import kotlinx.coroutines.flow.Flow

interface WalletRepository {

    suspend fun getRemoteBitcoinState() : ResultState<NetworkEntity>
    suspend fun getLocalBitcoinState() : Flow<NetworkEntity?>
    suspend fun cacheBitcoinState(bitcoinState: NetworkEntity)
    suspend fun getWalletBalance() : Flow<NetworkEntity?>
    suspend fun updateWalletBalance(walletBalance: NetworkEntity)
}