package com.pb.funora.currency.data

import com.pb.funora.currency.model.MarketItem
import kotlin.random.Random

class MarketplaceServiceImpl(
    private val currencyService: CurrencyServiceImpl
) : MarketplaceService {

    private val items = listOf(
        MarketItem("1", "Sword", 100, "A sharp blade"),
        MarketItem("2", "Shield", 150, "Protect yourself"),
        MarketItem("3", "Potion", 50, "Heals you")
    )

    override suspend fun fetchItems(): List<MarketItem> = items

    override suspend fun purchaseItem(itemId: String): Boolean {
        val item = items.find { it.itemId == itemId } ?: return false
        return currencyService.marketPurchase(item.price)
    }
}
