package com.pb.funora.games.okey.repository

import com.pb.funora.games.okey.model.GameState
import com.pb.funora.games.okey.model.Tile

/**
 * Repository interface for game actions.
 */
interface GameRepository {
    suspend fun startGame()
    suspend fun play(tile: Tile)
    suspend fun draw(): Tile
    suspend fun getState(): GameState
}
