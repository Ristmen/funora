package com.pb.funora.currency.model

import kotlinx.serialization.Serializable

@Serializable
enum class TransactionType {
    EARN,
    SPEND,
    STAKE,
    UNSTAKE,
    MARKET_PURCHASE
}
