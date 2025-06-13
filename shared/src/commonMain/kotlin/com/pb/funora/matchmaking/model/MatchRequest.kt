package com.pb.funora.matchmaking.model

data class MatchRequest(
    val playerId: String,
    val gameId: String,
    val timestamp: Long
)
