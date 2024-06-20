package com.pfv.cryptotracker.domain.use_case

import com.pfv.cryptotracker.data.dvo.WalletBalanceDvo
import com.pfv.cryptotracker.domain.NetworkEntity
import com.pfv.cryptotracker.domain.ResultState
import com.pfv.cryptotracker.domain.repository.WalletRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WalletBalanceOperationsUseCase @Inject constructor(
    private val repository: WalletRepository,
) {

    fun getWalletBalance() : Flow<ResultState<NetworkEntity>> = flow {

        repository.getWalletBalance().collect {
            emit(ResultState.Success(it))
        }
    }

    suspend fun updateWalletBalance(walletBalanceDvo: WalletBalanceDvo) {

        repository.updateWalletBalance(walletBalance = walletBalanceDvo)
    }
}