package com.pfv.cryptotracker.domain.use_case

import androidx.paging.PagingSource
import com.pfv.cryptotracker.data.dvo.TransactionDvo
import com.pfv.cryptotracker.data.dvo.TransactionsDvo
import com.pfv.cryptotracker.data.dvo.WalletBalanceDvo
import com.pfv.cryptotracker.data.local.entity.TransactionEntity
import com.pfv.cryptotracker.domain.NetworkEntity
import com.pfv.cryptotracker.domain.ResultState
import com.pfv.cryptotracker.domain.repository.WalletRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransactionsOperationsUseCase @Inject constructor(
    private val repository: WalletRepository,
) {

    suspend fun makeTransaction(transaction: TransactionDvo) {

        repository.makeTransaction(transaction = transaction)
    }

    fun getAllTransactions() : PagingSource<Int, TransactionEntity> {
        return repository.getAllTransactions()
    }
}