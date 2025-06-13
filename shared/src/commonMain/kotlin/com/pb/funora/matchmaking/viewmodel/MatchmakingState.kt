package com.pb.funora.matchmaking.viewmodel

import com.pb.funora.matchmaking.model.PlayerInfo

data class MatchmakingState(
    val isSearching: Boolean = false,
    val opponent: PlayerInfo? = null,
    val errorMessage: String? = null
)
