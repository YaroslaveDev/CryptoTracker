package com.pfv.cryptotracker.di

import android.app.Application
import androidx.room.Room
import com.pfv.cryptotracker.data.constants.LocalStorageConstants.WALLET_DATABASE
import com.pfv.cryptotracker.data.local.database.WalletDatabase
import com.pfv.cryptotracker.data.mapper.WalletMapper
import com.pfv.cryptotracker.data.remote.api.BitcoinStateService
import com.pfv.cryptotracker.data.repository.WalletRepositoryImpl
import com.pfv.cryptotracker.domain.repository.WalletRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideBitcoinCurrentStateApi(retrofit: Retrofit): BitcoinStateService =
        retrofit.create(BitcoinStateService::class.java)

    @Provides
    @Singleton
    fun provideWalletRepository(
        service: BitcoinStateService,
        localDb: WalletDatabase,
        walletMapper: WalletMapper
    ): WalletRepository =
        WalletRepositoryImpl(service, localDb, walletMapper)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : WalletDatabase =
        Room.databaseBuilder(app, WalletDatabase::class.java, WALLET_DATABASE)
            .build()

}