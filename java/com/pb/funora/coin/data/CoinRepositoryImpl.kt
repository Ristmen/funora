// coin/src/main/java/com/pb/funora/coin/data/CoinRepositoryImpl.kt
package com.pb.funora.coin.data

import com.google.firebase.firestore.FirebaseFirestore

class CoinRepositoryImpl(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) : CoinRepository {
    override suspend fun getBalance(userId: String): Long {
        // TODO: implement firestore retrieval
        return 0L
    }

    override suspend fun transfer(from: String, to: String, amount: Long): Boolean {
        // TODO: implement atomic transaction
        return false
    }
}
