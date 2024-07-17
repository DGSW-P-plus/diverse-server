package dev.jombi.diverse.business.chat.message.dto

import jakarta.validation.constraints.NotBlank
import java.util.UUID

data class ChatMessageDto(
    val roomId: UUID,
    val message: String,
    val userId: Long
)