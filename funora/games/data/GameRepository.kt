package com.pb.funora.games.data

import com.pb.funora.games.data.models.ReflexTrapConfig
import com.pb.funora.games.data.viewmodel.ReflexTrapViewModel.GameState
import kotlinx.coroutines.flow.StateFlow

/**
 * Oyunlar için genel veri katmanı arayüzü.
 */
interface GameRepository {
    // Reflex Trap
    fun startReflexTrap(config: ReflexTrapConfig)
    fun hitReflexTrap()
    fun retryReflexTrap()
    val reflexTrapTimeLeft: StateFlow<Int>
    val reflexTrapScore: StateFlow<Int>
    val reflexTrapGameState: StateFlow<GameState>
}
