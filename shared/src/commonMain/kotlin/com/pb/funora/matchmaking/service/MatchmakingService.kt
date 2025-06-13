package com.pb.funora.matchmaking.service

import com.pb.funora.matchmaking.model.MatchRequest
import com.pb.funora.matchmaking.model.MatchResult

interface MatchmakingService {
    suspend fun findMatch(request: MatchRequest): MatchResult
    suspend fun cancelMatch(request: MatchRequest)
}
