package com.pb.funora.coin.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
// 1. Material3 tema
import com.pb.funora.ui.theme.FunoraTheme
// 2. CoinHistoryScreen
import com.pb.funora.coin.ui.CoinHistoryScreen

class CoinTransactionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FunoraTheme {
                // CoinHistoryScreen’i çağırıyoruz
                CoinHistoryScreen()
            }
        }
    }
}
