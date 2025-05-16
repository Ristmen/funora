package com.pb.funora.games.data

import com.pb.funora.games.data.models.ReflexTrapConfig
import com.pb.funora.games.data.viewmodel.ReflexTrapViewModel.GameState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * GameRepository arayüzünün basit bir in-memory implementasyonu.
 */
class GameRepositoryImpl : GameRepository {
    private val _timeLeft = MutableStateFlow(0)
    override val reflexTrapTimeLeft: StateFlow<Int> = _timeLeft

    private val _score = MutableStateFlow(0)
    override val reflexTrapScore: StateFlow<Int> = _score

    private val _state = MutableStateFlow(GameState.NotStarted)
    override val reflexTrapGameState: StateFlow<GameState> = _state

    private var timerJob: Job? = null

    override fun startReflexTrap(config: ReflexTrapConfig) {
        timerJob?.cancel()
        _score.value = 0
        _timeLeft.value = config.totalTimeSec
        _state.value = GameState.Playing

        // Zamanlayıcı
        timerJob = CoroutineScope(Dispatchers.Default).launch {
            while (_timeLeft.value > 0) {
                delay(1000L)
                _timeLeft.update { it - 1 }
            }
            _state.value = GameState.Finished
        }
    }

    override fun hitReflexTrap() {
        if (_state.value == GameState.Playing) {
            _score.update { it + 1 }
        }
    }

    override fun retryReflexTrap() {
        startReflexTrap(ReflexTrapConfig())
    }
}
