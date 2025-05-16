// coin/src/main/java/com/pb/funora/coin/data/CoinRepository.kt
package com.pb.funora.coin.data

interface CoinRepository {
    suspend fun getBalance(userId: String): Long
    suspend fun transfer(from: String, to: String, amount: Long): Boolean
}
