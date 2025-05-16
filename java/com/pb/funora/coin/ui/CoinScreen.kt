package com.pb.funora.coin.ui

import androidx.compose.ui.Alignment
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pb.funora.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinScreen(onHistory: () -> Unit) {
    var balance by remember { mutableStateOf(0) }
    Scaffold(
        topBar = { SmallTopAppBar(title = { Text(stringResource(R.string.coin_balance_title)) }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(R.string.your_balance))
            AnimatedContent(targetState = balance) { bal ->
                Text("$bal 🔸", style = MaterialTheme.typography.displaySmall)
            }
            Spacer(Modifier.height(16.dp))
            Button(onClick = { /* reklam izle + coin */ }) {
                Text(stringResource(R.string.watch_ad))
            }
            Spacer(Modifier.height(24.dp))
            TextButton(onClick = onHistory) {
                Text(stringResource(R.string.history_tab))
            }
        }
    }
}
