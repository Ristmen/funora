// GamePlayScreen.kt
package com.pb.funora.games.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.pb.funora.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamePlayScreen(
    gameName: String,
    timerSec: Int,
    onAction: (String) -> Unit,
    onExit: () -> Unit
) {
    var timeLeft by remember { mutableStateOf(timerSec) }
    LaunchedEffect(timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(gameName) },
                actions = {
                    Text(
                        "$timeLeft ${stringResource(R.string.seconds)}",
                        Modifier.padding(end = 12.dp)
                    )
                    IconButton(onClick = onExit) {
                        Icon(Icons.Default.ExitToApp, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .border(2.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), MaterialTheme.shapes.medium),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(R.string.game_area_placeholder))
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = { onAction("play") }) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null)
                }
                IconButton(onClick = { onAction("pass") }) {
                    Icon(Icons.Default.SkipNext, contentDescription = null)
                }
                IconButton(onClick = { onAction("chat") }) {
                    Icon(Icons.Default.Chat, contentDescription = null)
                }
            }
        }
    }
}
