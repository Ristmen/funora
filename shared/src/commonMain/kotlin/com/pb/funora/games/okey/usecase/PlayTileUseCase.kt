package com.pb.funora.games.okey.usecase

import com.pb.funora.games.okey.model.Tile
import com.pb.funora.games.okey.repository.GameRepository

class PlayTileUseCase(private val repository: GameRepository) {
    suspend operator fun invoke(tile: Tile) {
        repository.play(tile)
    }
}
