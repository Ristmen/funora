package com.pb.funora.games.okey.model

import com.pb.funora.games.okey.engine.TileColor

data class Tile(
    val id: String,
    val color: TileColor,
    val number: Int
)
