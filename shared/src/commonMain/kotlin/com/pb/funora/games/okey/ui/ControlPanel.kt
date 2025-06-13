package com.pb.funora.games.okey.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ControlPanel(onDraw: () -> Unit, onPlay: () -> Unit, currentPlayer: Int) {
    Row(Modifier.padding(8.dp)) {
        Button(onClick = onDraw, modifier = Modifier.padding(end = 8.dp)) {
            Text("Draw")
        }
        Button(onClick = onPlay, modifier = Modifier.padding(end = 8.dp)) {
            Text("Play")
        }
        Text("Player $currentPlayer turn")
    }
}
