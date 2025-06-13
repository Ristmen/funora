package com.pb.funora.currency.viewmodel

import com.pb.funora.currency.model.MarketItem

data class MarketplaceState(
    val items: List<MarketItem> = emptyList(),
    val purchaseResult: Boolean? = null,
    val error: String? = null
)
