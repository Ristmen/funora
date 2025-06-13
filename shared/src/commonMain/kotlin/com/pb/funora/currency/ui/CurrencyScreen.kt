package com.pb.funora.currency.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.pb.funora.currency.viewmodel.CurrencyViewModel

@Composable
fun CurrencyScreen(viewModel: CurrencyViewModel) {
    val state = viewModel.state.collectAsState().value

    Column {
        Text(text = "Balance: ${'$'}{state.balance}")
        Row {
            Button(onClick = { /* open stake dialog externally */ }) {
                Text("Stake")
            }
            Button(onClick = { /* open unstake dialog externally */ }) {
                Text("Unstake")
            }
        }
        LazyColumn {
            items(state.transactions) { tx ->
                Text("${'$'}{tx.type}: ${'$'}{tx.amount}")
            }
        }
    }
}
