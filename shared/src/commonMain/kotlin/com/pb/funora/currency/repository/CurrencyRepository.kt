package com.pb.funora.currency.repository

import com.pb.funora.currency.data.CurrencyService
import com.pb.funora.currency.model.CoinTransaction

class CurrencyRepository(private val service: CurrencyService) {
    suspend fun getBalance(): Long = service.fetchBalance()
    suspend fun getTransactions(): List<CoinTransaction> = service.fetchTransactions()
    suspend fun stake(amount: Long) = service.stake(amount)
    suspend fun unstake(amount: Long) = service.unstake(amount)
}
