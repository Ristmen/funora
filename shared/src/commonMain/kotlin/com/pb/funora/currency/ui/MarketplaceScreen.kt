package com.pb.funora.currency.ui

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.pb.funora.currency.viewmodel.MarketplaceViewModel

@Composable
fun MarketplaceScreen(viewModel: MarketplaceViewModel) {
    val state = viewModel.state.collectAsState().value

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(state.items) { item ->
            Card {
                Text(item.name)
                Text("${'$'}{item.price}")
                Button(onClick = { viewModel.purchase(item.itemId) }) {
                    Text("Satın Al")
                }
            }
        }
    }
}
