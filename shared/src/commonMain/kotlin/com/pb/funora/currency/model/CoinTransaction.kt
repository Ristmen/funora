package com.pb.funora.currency.model

import kotlinx.serialization.Serializable

@Serializable
data class CoinTransaction(
    val id: String,
    val amount: Long,
    val type: TransactionType,
    val timestamp: Long
)
