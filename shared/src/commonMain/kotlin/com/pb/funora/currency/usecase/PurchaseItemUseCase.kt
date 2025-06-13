package com.pb.funora.currency.usecase

import com.pb.funora.currency.repository.MarketplaceRepository

class PurchaseItemUseCase(private val repository: MarketplaceRepository) {
    suspend operator fun invoke(itemId: String): Boolean = repository.purchase(itemId)
}
