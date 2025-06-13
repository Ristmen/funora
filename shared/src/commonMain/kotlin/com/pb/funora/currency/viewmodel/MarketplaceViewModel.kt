package com.pb.funora.currency.viewmodel

import com.pb.funora.currency.usecase.GetMarketItemsUseCase
import com.pb.funora.currency.usecase.PurchaseItemUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MarketplaceViewModel(
    private val getItems: GetMarketItemsUseCase,
    private val purchaseUseCase: PurchaseItemUseCase,
    scope: CoroutineScope? = null
) {
    private val viewModelScope = scope ?: CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _state = MutableStateFlow(MarketplaceState())
    val state: StateFlow<MarketplaceState> = _state

    fun loadItems() {
        viewModelScope.launch {
            runCatching { getItems() }
                .onSuccess { list -> _state.update { it.copy(items = list, error = null) } }
                .onFailure { e -> _state.update { it.copy(error = e.message) } }
        }
    }

    fun purchase(itemId: String) {
        viewModelScope.launch {
            runCatching { purchaseUseCase(itemId) }
                .onSuccess { result -> _state.update { it.copy(purchaseResult = result, error = null) } }
                .onFailure { e -> _state.update { it.copy(error = e.message) } }
        }
    }
}
