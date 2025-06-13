package com.pb.funora.currency.usecase

import com.pb.funora.currency.model.MarketItem
import com.pb.funora.currency.repository.MarketplaceRepository

class GetMarketItemsUseCase(private val repository: MarketplaceRepository) {
    suspend operator fun invoke(): List<MarketItem> = repository.getItems()
}
