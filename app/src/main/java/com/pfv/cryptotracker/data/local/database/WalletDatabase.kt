package com.pfv.cryptotracker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pfv.cryptotracker.data.local.dao.WalletDao
import com.pfv.cryptotracker.data.local.entity.BalanceEntity
import com.pfv.cryptotracker.data.local.entity.BitcoinStateEntity
import com.pfv.cryptotracker.data.local.entity.TransactionEntity

@Database(entities = [BitcoinStateEntity::class, BalanceEntity::class, TransactionEntity::class], version = 1)
abstract class WalletDatabase : RoomDatabase() {

    abstract fun walletDao(): WalletDao
}