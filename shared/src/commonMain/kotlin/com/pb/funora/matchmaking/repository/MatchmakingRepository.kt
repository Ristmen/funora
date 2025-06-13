package com.pb.funora.matchmaking.repository

import com.pb.funora.matchmaking.model.MatchRequest
import com.pb.funora.matchmaking.model.MatchResult
import com.pb.funora.matchmaking.service.MatchmakingService

class MatchmakingRepository(private val service: MatchmakingService) {
    suspend fun findMatch(request: MatchRequest): MatchResult = service.findMatch(request)
    suspend fun cancelMatch(request: MatchRequest) = service.cancelMatch(request)
}
