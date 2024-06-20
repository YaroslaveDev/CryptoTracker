package com.pfv.cryptotracker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pfv.cryptotracker.data.local.entity.BalanceEntity
import com.pfv.cryptotracker.data.local.entity.BitcoinStateEntity
import com.pfv.cryptotracker.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao {
    @Query("SELECT * FROM balance WHERE id = 1")
    fun getBalance(): Flow<BalanceEntity>

    @Query("SELECT * FROM bitcoin_state WHERE id = 1")
    fun getCurrentBitcoinState(): Flow<BitcoinStateEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCurrentBitcoinState(stateEntity: BitcoinStateEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBalance(balance: BalanceEntity)

    @Query("SELECT * FROM `transaction` ORDER BY timestamp DESC LIMIT :limit OFFSET :offset")
    suspend fun getTransactions(limit: Int, offset: Int): List<TransactionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)
}