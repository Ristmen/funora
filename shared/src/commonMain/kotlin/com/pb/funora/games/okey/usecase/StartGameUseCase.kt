package com.pb.funora.games.okey.usecase

import com.pb.funora.games.okey.repository.GameRepository

class StartGameUseCase(private val repository: GameRepository) {
    suspend operator fun invoke() {
        repository.startGame()
    }
}
