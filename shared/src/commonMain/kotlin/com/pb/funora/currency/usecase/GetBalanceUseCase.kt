package com.pb.funora.currency.usecase

import com.pb.funora.currency.repository.CurrencyRepository

class GetBalanceUseCase(private val repository: CurrencyRepository) {
    suspend operator fun invoke(): Long = repository.getBalance()
}
