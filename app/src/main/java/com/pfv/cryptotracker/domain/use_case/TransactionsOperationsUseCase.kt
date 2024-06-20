package com.pfv.cryptotracker.domain.use_case

import com.pfv.cryptotracker.data.dvo.TransactionsDvo
import com.pfv.cryptotracker.data.dvo.WalletBalanceDvo
import com.pfv.cryptotracker.domain.NetworkEntity
import com.pfv.cryptotracker.domain.ResultState
import com.pfv.cryptotracker.domain.repository.WalletRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransactionsOperationsUseCase @Inject constructor(
    private val repository: WalletRepository,
) {

    suspend fun makeTransaction(transactionsDvo: TransactionsDvo) {

        repository.makeTransaction(transaction = transactionsDvo)
    }
}