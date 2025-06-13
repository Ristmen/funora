package com.pb.funora.games.okey.repository

import com.pb.funora.games.okey.engine.OkeyEngine
import com.pb.funora.games.okey.model.GameState
import com.pb.funora.games.okey.model.Tile

/**
 * In-memory implementation of [GameRepository] using [OkeyEngine].
 */
class GameRepositoryImpl(
    private val engine: OkeyEngine = OkeyEngine()
) : GameRepository {

    override suspend fun startGame() {
        engine.dealTiles()
    }

    override suspend fun play(tile: Tile) {
        engine.playTile(tile)
    }

    override suspend fun draw(): Tile {
        return engine.drawTile()
    }

    override suspend fun getState(): GameState {
        return engine.getState()
    }
}
