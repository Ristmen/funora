package com.pb.funora.games.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pb.funora.games.data.GameRepository
import com.pb.funora.games.data.GameRepositoryImpl
import com.pb.funora.games.data.models.ReflexTrapConfig
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Refleks Kapanı oyununun ViewModel’i.
 */
class ReflexTrapViewModel(
    private val repository: GameRepository = GameRepositoryImpl(),
    val config: ReflexTrapConfig = ReflexTrapConfig()
) : ViewModel() {

    val timeLeft: StateFlow<Int> = repository.reflexTrapTimeLeft
    val score: StateFlow<Int> = repository.reflexTrapScore
    val gameState: StateFlow<GameState> = repository.reflexTrapGameState

    fun startGame() = viewModelScope.launch {
        repository.startReflexTrap(config)
    }

    fun onHit() = repository.hitReflexTrap()

    fun retry() = viewModelScope.launch {
        repository.retryReflexTrap()
    }

    enum class GameState { NotStarted, Playing, Finished }
}
