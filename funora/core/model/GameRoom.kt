// GameRoom.kt
package com.pb.funora.core.model
data class GameRoom(
    val name: String,
    val currentPlayers: Int,
    val maxPlayers: Int,
    val entryFee: Int
)
