package com.pb.funora.home.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pb.funora.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel, onGameClick: (String) -> Unit) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadHomeData() }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    state.data?.let { data ->
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(
                text = "Coins: ${data.coinBalance}",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                data.announcements.forEach { announcement ->
                    Card(modifier = Modifier.padding(end = 8.dp).width(250.dp)) {
                        Text(text = announcement, modifier = Modifier.padding(16.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Önerilen Oyunlar", style = MaterialTheme.typography.titleMedium)
            LazyRow {
                items(data.featuredGames.size) { index ->
                    val game = data.featuredGames[index]
                    GameCard(game) { onGameClick(game.gameId) }
                }
            }
        }
    }

    state.errorMessage?.let { message ->
        Snackbar { Text(text = message) }
    }
}
