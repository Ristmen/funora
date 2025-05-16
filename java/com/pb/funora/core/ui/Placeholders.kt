// Placeholders.kt
package com.pb.funora.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable fun SplashScreen() = Placeholder("SplashScreen")
@Composable fun FriendsScreen() = Placeholder("FriendsScreen")
@Composable fun CoinBalanceScreen() = Placeholder("CoinBalanceScreen")
@Composable fun StoreScreen() = Placeholder("StoreScreen")
// ↓ Bunu SİLİN veya yorum satırına alın:
// @Composable fun SettingsScreen() = Placeholder("SettingsScreen")
@Composable fun ChatScreen(
    messages: List<Any>, onSendText: (String)->Unit, onRecord: ()->Unit
) = Placeholder("ChatScreen")
// …daha önce hataya sebep olan ama artık gerçek hali var olan ekran stub’larını da kaldırın…

@Composable
private fun Placeholder(name: String) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("TODO: $name henüz implement edilmedi")
    }
}
