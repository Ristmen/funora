package com.pb.funora.currency.data

import com.pb.funora.currency.model.MarketItem

interface MarketplaceService {
    suspend fun fetchItems(): List<MarketItem>
    suspend fun purchaseItem(itemId: String): Boolean
}
