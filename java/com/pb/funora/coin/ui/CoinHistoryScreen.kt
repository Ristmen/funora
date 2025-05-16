package com.pb.funora.coin.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Örnek veri modeli (kendi veri kaynağına göre değiştirebilirsin)
data class CoinTransaction(
    val date: String,
    val amount: Int,
    val description: String
)

@Composable
fun CoinHistoryScreen(
    transactions: List<CoinTransaction> = sampleTransactions()
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Coin Geçmişi",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(transactions) { tx ->
                CoinTransactionItem(tx)
            }
        }
    }
}

@Composable
private fun CoinTransactionItem(tx: CoinTransaction) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = tx.date, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "${tx.amount} coin", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = tx.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

// Test için örnek liste
private fun sampleTransactions() = listOf(
    CoinTransaction("2025-05-01", +50, "Günlük giriş ödülü"),
    CoinTransaction("2025-05-02", -20, "Oyun harcaması"),
    CoinTransaction("2025-05-03", +100, "Arkadaş davet bonusu")
)
