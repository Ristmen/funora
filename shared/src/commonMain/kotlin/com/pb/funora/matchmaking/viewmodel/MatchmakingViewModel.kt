package com.pb.funora.matchmaking.viewmodel

import com.pb.funora.matchmaking.model.MatchRequest
import com.pb.funora.matchmaking.usecase.FindMatchUseCase
import com.pb.funora.matchmaking.usecase.CancelMatchUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MatchmakingViewModel(
    private val findMatchUseCase: FindMatchUseCase,
    private val cancelMatchUseCase: CancelMatchUseCase,
    private val coroutineContext: CoroutineContext = Dispatchers.Default
) {
    private val _state = MutableStateFlow(MatchmakingState())
    val state: StateFlow<MatchmakingState> = _state

    private var searchJob: Job? = null

    fun startSearch(request: MatchRequest) {
        if (searchJob != null) return
        _state.value = MatchmakingState(isSearching = true)
        searchJob = CoroutineScope(coroutineContext).launch {
            val result = findMatchUseCase(request)
            if (result.success) {
                _state.value = MatchmakingState(isSearching = false, opponent = result.opponent)
            } else {
                _state.value = MatchmakingState(isSearching = false, errorMessage = result.error)
            }
            searchJob = null
        }
    }

    fun cancelSearch(request: MatchRequest) {
        searchJob?.cancel()
        CoroutineScope(coroutineContext).launch {
            cancelMatchUseCase(request)
        }
        _state.value = MatchmakingState(isSearching = false)
        searchJob = null
    }
}
