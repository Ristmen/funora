package com.pb.funora.games.okey.model

/**
 * Holds the current state of the game.
 */
data class GameState(
    val tilesOnHand: List<Tile>,
    val tilesOnTable: List<Tile>,
    val currentPlayer: Int
)
