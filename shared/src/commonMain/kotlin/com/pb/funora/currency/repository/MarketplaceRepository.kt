package com.pb.funora.currency.repository

import com.pb.funora.currency.data.MarketplaceService
import com.pb.funora.currency.model.MarketItem

class MarketplaceRepository(private val service: MarketplaceService) {
    suspend fun getItems(): List<MarketItem> = service.fetchItems()
    suspend fun purchase(itemId: String): Boolean = service.purchaseItem(itemId)
}
