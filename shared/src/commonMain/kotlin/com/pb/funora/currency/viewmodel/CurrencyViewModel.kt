package com.pb.funora.currency.viewmodel

import com.pb.funora.currency.usecase.GetBalanceUseCase
import com.pb.funora.currency.usecase.GetTransactionsUseCase
import com.pb.funora.currency.usecase.StakeUseCase
import com.pb.funora.currency.usecase.UnstakeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CurrencyViewModel(
    private val getBalance: GetBalanceUseCase,
    private val getTransactions: GetTransactionsUseCase,
    private val stakeUseCase: StakeUseCase,
    private val unstakeUseCase: UnstakeUseCase,
    scope: CoroutineScope? = null
) {
    private val viewModelScope = scope ?: CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _state = MutableStateFlow(CurrencyState())
    val state: StateFlow<CurrencyState> = _state

    fun loadBalance() {
        viewModelScope.launch {
            runCatching { getBalance() }
                .onSuccess { balance -> _state.update { it.copy(balance = balance, error = null) } }
                .onFailure { e -> _state.update { it.copy(error = e.message) } }
        }
    }

    fun loadTransactions() {
        viewModelScope.launch {
            runCatching { getTransactions() }
                .onSuccess { list -> _state.update { it.copy(transactions = list, error = null) } }
                .onFailure { e -> _state.update { it.copy(error = e.message) } }
        }
    }

    fun stake(amount: Long) {
        viewModelScope.launch {
            runCatching { stakeUseCase(amount) }
                .onSuccess { loadBalance(); loadTransactions() }
                .onFailure { e -> _state.update { it.copy(error = e.message) } }
        }
    }

    fun unstake(amount: Long) {
        viewModelScope.launch {
            runCatching { unstakeUseCase(amount) }
                .onSuccess { loadBalance(); loadTransactions() }
                .onFailure { e -> _state.update { it.copy(error = e.message) } }
        }
    }
}
