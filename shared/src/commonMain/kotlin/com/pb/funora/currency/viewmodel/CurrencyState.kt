package com.pb.funora.currency.viewmodel

import com.pb.funora.currency.model.CoinTransaction
import com.pb.funora.currency.model.StakeInfo

data class CurrencyState(
    val balance: Long = 0L,
    val transactions: List<CoinTransaction> = emptyList(),
    val stakeInfo: StakeInfo? = null,
    val error: String? = null
)
