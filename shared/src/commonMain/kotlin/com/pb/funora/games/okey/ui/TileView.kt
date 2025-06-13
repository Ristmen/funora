package com.pb.funora.games.okey.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pb.funora.games.okey.engine.TileColor
import com.pb.funora.games.okey.model.Tile

@Composable
fun TileView(tile: Tile, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(40.dp)
            .background(colorForTile(tile.color))
    ) {
        Text(text = tile.number.toString(), color = Color.White)
    }
}

private fun colorForTile(color: TileColor): Color = when (color) {
    TileColor.RED -> Color.Red
    TileColor.BLUE -> Color.Blue
    TileColor.GREEN -> Color.Green
    TileColor.BLACK -> Color.DarkGray
}
