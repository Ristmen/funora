package com.pb.funora.home.model

import kotlinx.serialization.Serializable

@Serializable
data class GameCardData(
    val gameId: String,
    val title: String,
    val playerCount: Int,
    val maxPlayers: Int
)
