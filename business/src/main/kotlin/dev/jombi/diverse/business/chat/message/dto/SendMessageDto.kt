package dev.jombi.diverse.business.chat.message.dto

import java.util.UUID

data class SendMessageDto(
    val roomId: UUID,
    val message: String? = null,
)