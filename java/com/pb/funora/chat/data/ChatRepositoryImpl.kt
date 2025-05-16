// chat/src/main/java/com/pb/funora/chat/data/ChatRepositoryImpl.kt
package com.pb.funora.chat.data

import com.google.firebase.firestore.FirebaseFirestore

class ChatRepositoryImpl(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) : ChatRepository {
    override suspend fun sendMessage(roomId: String, message: String) {
        // TODO: send message to firestore
    }

    override suspend fun getMessages(roomId: String): List<String> {
        // TODO: fetch messages
        return emptyList()
    }
}
