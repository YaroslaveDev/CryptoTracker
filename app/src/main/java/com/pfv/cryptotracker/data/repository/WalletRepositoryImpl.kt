package com.pfv.cryptotracker.data.repository

import androidx.room.withTransaction
import com.pfv.cryptotracker.data.dvo.CurrentBitcoinStateDvo
import com.pfv.cryptotracker.data.local.database.WalletDatabase
import com.pfv.cryptotracker.data.local.entity.BitcoinStateEntity
import com.pfv.cryptotracker.data.mapper.WalletMapper
import com.pfv.cryptotracker.data.remote.api.BitcoinStateService
import com.pfv.cryptotracker.data.remote.network.toResultState
import com.pfv.cryptotracker.domain.NetworkEntity
import com.pfv.cryptotracker.domain.ResultState
import com.pfv.cryptotracker.domain.repository.WalletRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WalletRepositoryImpl @Inject constructor(
    private val service: BitcoinStateService,
    private val localDb: WalletDatabase,
    private val walletMapper: WalletMapper,
) : WalletRepository {

    override suspend fun getRemoteBitcoinState(): ResultState<NetworkEntity> {
        return service.getCurrentBitcoinState().toResultState { walletMapper.bitcoinStateDtoToDvo(it) }
    }

    override suspend fun getLocalBitcoinState(): Flow<CurrentBitcoinStateDvo> = flow {

        localDb.walletDao().getCurrentBitcoinState().collect {

            emit(walletMapper.bitcoinStateDboToDvo(it))
        }
    }

    override suspend fun cacheBitcoinState(bitcoinState: NetworkEntity) {

        localDb.withTransaction {
            localDb.walletDao().updateCurrentBitcoinState(
                walletMapper.bitcoinStateDvoToDbo(
                    bitcoinState as CurrentBitcoinStateDvo
                )
            )
        }
    }
}