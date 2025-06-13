package com.pb.funora.currency.data

import com.pb.funora.currency.model.CoinTransaction

interface CurrencyService {
    suspend fun fetchBalance(): Long
    suspend fun fetchTransactions(): List<CoinTransaction>
    suspend fun stake(amount: Long)
    suspend fun unstake(amount: Long)
}
