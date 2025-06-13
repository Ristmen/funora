package com.pb.funora.matchmaking.usecase

import com.pb.funora.matchmaking.model.MatchRequest
import com.pb.funora.matchmaking.model.MatchResult
import com.pb.funora.matchmaking.repository.MatchmakingRepository

class FindMatchUseCase(private val repository: MatchmakingRepository) {
    suspend operator fun invoke(request: MatchRequest): MatchResult {
        return repository.findMatch(request)
    }
}
