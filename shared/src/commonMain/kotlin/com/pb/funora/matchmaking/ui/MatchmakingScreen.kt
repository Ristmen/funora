package com.pb.funora.matchmaking.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pb.funora.matchmaking.model.MatchRequest
import com.pb.funora.matchmaking.viewmodel.MatchmakingViewModel

@Composable
fun MatchmakingScreen(viewModel: MatchmakingViewModel, playerId: String, gameId: String) {
    val state by viewModel.state.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            state.opponent != null -> {
                val opponent = state.opponent
                Text(text = "Rakip: ${'$'}{opponent.name} (${opponent.rating})" + if (opponent.isBot) " [Bot]" else "")
            }
            state.isSearching -> {
                CircularProgressIndicator()
            }
            state.errorMessage != null -> {
                Text(text = state.errorMessage ?: "")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (!state.isSearching && state.opponent == null) {
            Button(onClick = {
                val request = MatchRequest(playerId, gameId, System.currentTimeMillis())
                viewModel.startSearch(request)
            }) {
                Text("Ara")
            }
        } else if (state.isSearching) {
            Button(onClick = {
                val request = MatchRequest(playerId, gameId, System.currentTimeMillis())
                viewModel.cancelSearch(request)
            }) {
                Text("Vazgeç")
            }
        }
    }
}
