package com.pfv.cryptotracker.domain.use_case

import com.bumptech.glide.load.engine.Resource
import com.pfv.cryptotracker.data.dvo.CurrentBitcoinStateDvo
import com.pfv.cryptotracker.domain.NetworkEntity
import com.pfv.cryptotracker.domain.ResultState
import com.pfv.cryptotracker.domain.repository.WalletRepository
import com.pfv.cryptotracker.ext.isDiffMoreThatHour
import com.pfv.cryptotracker.ui.ext.isNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBitcoinStateUseCase @Inject constructor(
    private val repository: WalletRepository,

    ) {
    operator fun invoke(): Flow<ResultState<NetworkEntity>> = flow {

        updateFrequencyHelper(
            onRefreshDataFlow = {
                val currentState = repository.getRemoteBitcoinState()

                when (currentState) {
                    is ResultState.Success -> {

                        currentState.data?.let {
                            repository.cacheBitcoinState(
                                (it as CurrentBitcoinStateDvo)
                            )
                        }

                        emit(ResultState.Success(currentState.data))
                    }

                    is ResultState.Error -> {
                        repository.getLocalBitcoinState().collect {
                            emit(ResultState.Success(it))
                        }
                    }
                }
            },
            onEmitCachedData = {
                repository.getLocalBitcoinState().collect {
                    emit(ResultState.Success(it))
                }
            }
        )
    }

    private suspend fun updateFrequencyHelper(onRefreshDataFlow: suspend () -> Unit, onEmitCachedData: suspend () -> Unit) {

        repository.getLocalBitcoinState().collect {

            if (
                it.isNull() ||
                (it as? CurrentBitcoinStateDvo)?.updatedAt?.isDiffMoreThatHour() == true
            ) {
                onRefreshDataFlow()
            } else {
                onEmitCachedData()
            }
        }
    }
}