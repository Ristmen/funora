package com.pb.funora.matchmaking.usecase

import com.pb.funora.matchmaking.model.MatchRequest
import com.pb.funora.matchmaking.repository.MatchmakingRepository

class CancelMatchUseCase(private val repository: MatchmakingRepository) {
    suspend operator fun invoke(request: MatchRequest) {
        repository.cancelMatch(request)
    }
}
