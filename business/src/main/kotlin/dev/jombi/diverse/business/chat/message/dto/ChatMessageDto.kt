package dev.jombi.diverse.business.chat.message.dto

import java.time.LocalDateTime
import java.util.UUID

data class ChatMessageDto(
    val roomId: UUID,
    val message: String,
    val userId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
