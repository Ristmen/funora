package com.pb.funora.games.okey.ui

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.pb.funora.games.okey.model.Tile

@Composable
fun OkeyTable(tiles: List<Tile>, onTilePlayed: (Tile) -> Unit) {
    val tileState = remember { mutableStateListOf<Tile>().apply { addAll(tiles) } }

    FlowRow(Modifier.padding(8.dp)) {
        tileState.forEach { tile ->
            TileView(
                tile = tile,
                modifier = Modifier
                    .padding(4.dp)
                    .pointerInput(tile) {
                        detectDragGestures(onDragEnd = {
                            onTilePlayed(tile)
                            tileState.remove(tile)
                        })
                    }
            )
        }
    }
}
