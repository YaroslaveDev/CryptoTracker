package com.pfv.cryptotracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pfv.cryptotracker.data.constants.LocalStorageConstants
import com.pfv.cryptotracker.domain.NetworkEntity
import kotlinx.serialization.SerialName
import java.util.Date

@Entity(tableName = LocalStorageConstants.BITCOIN_STATE)
data class BitcoinStateEntity(
    @PrimaryKey
    val id: Int = 1,
    val code: String,
    val rate: String,
    val rateFloat: Float,
    val updatedAt: Date
) : NetworkEntity
