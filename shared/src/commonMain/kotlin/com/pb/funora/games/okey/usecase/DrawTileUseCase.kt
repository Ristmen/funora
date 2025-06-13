package com.pb.funora.games.okey.usecase

import com.pb.funora.games.okey.model.Tile
import com.pb.funora.games.okey.repository.GameRepository

class DrawTileUseCase(private val repository: GameRepository) {
    suspend operator fun invoke(): Tile {
        return repository.draw()
    }
}
