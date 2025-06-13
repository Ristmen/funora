package com.pb.funora.currency.model

import kotlinx.serialization.Serializable

@Serializable
data class MarketItem(
    val itemId: String,
    val name: String,
    val price: Long,
    val description: String
)
