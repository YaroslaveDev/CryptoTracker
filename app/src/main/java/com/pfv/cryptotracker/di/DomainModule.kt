package com.pfv.cryptotracker.di

import com.pfv.cryptotracker.domain.repository.WalletRepository
import com.pfv.cryptotracker.domain.use_case.GetBitcoinStateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideGetBitcoinCurrentStateUseCase(walletRepository: WalletRepository) =
        GetBitcoinStateUseCase(repository = walletRepository)

}