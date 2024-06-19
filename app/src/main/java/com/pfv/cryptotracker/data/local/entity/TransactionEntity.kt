package com.pfv.cryptotracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pfv.cryptotracker.data.constants.LocalStorageConstants.TRANSACTION_TABLE

@Entity(tableName = TRANSACTION_TABLE)
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Double,
    val category: String,
    val timestamp: Long
)