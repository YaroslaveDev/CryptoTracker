package com.pfv.cryptotracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pfv.cryptotracker.data.constants.LocalStorageConstants.BALANCE_TABLE

@Entity(tableName = BALANCE_TABLE)
data class BalanceEntity(
    @PrimaryKey
    val id: Int = 1,
    val amount: Double
)