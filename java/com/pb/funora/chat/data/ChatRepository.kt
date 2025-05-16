// chat/src/main/java/com/pb/funora/chat/data/ChatRepository.kt
package com.pb.funora.chat.data

interface ChatRepository {
    suspend fun sendMessage(roomId: String, message: String)
    suspend fun getMessages(roomId: String): List<String>
}
