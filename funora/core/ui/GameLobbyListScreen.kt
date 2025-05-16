// GameLobbyListScreen.kt
package com.pb.funora.core.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pb.funora.R
import com.pb.funora.core.model.GameRoom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameLobbyListScreen(
    gameName: String,
    rooms: List<GameRoom>,
    onJoin: (GameRoom) -> Unit,
    onCreate: () -> Unit
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(stringResource(R.string.rooms_title, gameName)) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onCreate) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->
        LazyColumn(
            Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(rooms) { room ->
                ElevatedCard(
                    onClick = { onJoin(room) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Row(
                        Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(Modifier.weight(1f)) {
                            Text(room.name)
                            Text(
                                text = "${room.currentPlayers}/${room.maxPlayers} " +
                                        stringResource(R.string.players)
                            )
                        }
                        Text(text = "${room.entryFee} 🔸")
                    }
                }
            }
        }
    }
}
