package com.pb.funora.currency.usecase

import com.pb.funora.currency.repository.CurrencyRepository

class UnstakeUseCase(private val repository: CurrencyRepository) {
    suspend operator fun invoke(amount: Long) = repository.unstake(amount)
}
