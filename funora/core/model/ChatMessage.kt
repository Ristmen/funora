// ChatMessage.kt
package com.pb.funora.core.model
data class ChatMessage(
    val id: String,
    val text: String,
    val isMine: Boolean,
    val timestamp: Long
)
