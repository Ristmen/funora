// CoinBalanceScreen.kt
package com.pb.funora.coin.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pb.funora.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinBalanceScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(stringResource(R.string.coin_balance_title)) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.your_balance) + ": 0",
                style = MaterialTheme.typography.headlineMedium
            )
            Button(onClick = { navController.navigate("coinHistory") }) {
                Text(stringResource(R.string.history_tab))
            }
            Button(onClick = { navController.navigate("store") }) {
                Text(stringResource(R.string.purchase_tab))
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { /* show ad logic */ }) {
                Text(stringResource(R.string.watch_ad))
            }
        }
    }
}

