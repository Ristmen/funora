package com.pb.funora.matchmaking.model

data class MatchResult(
    val success: Boolean,
    val opponent: PlayerInfo?,
    val error: String?
)
