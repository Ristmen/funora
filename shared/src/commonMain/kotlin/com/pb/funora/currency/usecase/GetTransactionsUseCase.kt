package com.pb.funora.currency.usecase

import com.pb.funora.currency.model.CoinTransaction
import com.pb.funora.currency.repository.CurrencyRepository

class GetTransactionsUseCase(private val repository: CurrencyRepository) {
    suspend operator fun invoke(): List<CoinTransaction> = repository.getTransactions()
}
