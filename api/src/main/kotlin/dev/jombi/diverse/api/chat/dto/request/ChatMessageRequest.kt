package dev.jombi.diverse.api.chat.dto.request

data class ChatMessageRequest(
    val roomId: String,
    val sender: String,
    val message: String
)