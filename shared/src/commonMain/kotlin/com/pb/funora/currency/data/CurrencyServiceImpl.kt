package com.pb.funora.currency.data

import com.pb.funora.currency.model.CoinTransaction
import com.pb.funora.currency.model.TransactionType
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.random.Random
import kotlin.system.getTimeMillis

class CurrencyServiceImpl : CurrencyService {
    private val mutex = Mutex()
    private var balance = 1000L
    private val transactions = mutableListOf<CoinTransaction>()
    private var stakedAmount = 0L

    override suspend fun fetchBalance(): Long = mutex.withLock { balance }

    override suspend fun fetchTransactions(): List<CoinTransaction> =
        mutex.withLock { transactions.toList() }

    override suspend fun stake(amount: Long) {
        mutex.withLock {
            if (amount <= balance && amount > 0) {
                balance -= amount
                stakedAmount += amount
                transactions += CoinTransaction(
                    id = Random.nextInt().toString(),
                    amount = amount,
                    type = TransactionType.STAKE,
                    timestamp = getTimeMillis()
                )
            }
        }
    }

    override suspend fun unstake(amount: Long) {
        mutex.withLock {
            if (amount <= stakedAmount && amount > 0) {
                stakedAmount -= amount
                balance += amount
                transactions += CoinTransaction(
                    id = Random.nextInt().toString(),
                    amount = amount,
                    type = TransactionType.UNSTAKE,
                    timestamp = getTimeMillis()
                )
            }
        }
    }

    internal suspend fun marketPurchase(amount: Long): Boolean = mutex.withLock {
        if (amount <= balance) {
            balance -= amount
            transactions += CoinTransaction(
                id = Random.nextInt().toString(),
                amount = amount,
                type = TransactionType.MARKET_PURCHASE,
                timestamp = getTimeMillis()
            )
            true
        } else {
            false
        }
    }
}
