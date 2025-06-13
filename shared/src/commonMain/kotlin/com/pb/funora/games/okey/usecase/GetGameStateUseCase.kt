package com.pb.funora.games.okey.usecase

import com.pb.funora.games.okey.model.GameState
import com.pb.funora.games.okey.repository.GameRepository

class GetGameStateUseCase(private val repository: GameRepository) {
    suspend operator fun invoke(): GameState {
        return repository.getState()
    }
}
