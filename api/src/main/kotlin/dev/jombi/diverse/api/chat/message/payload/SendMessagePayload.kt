package dev.jombi.diverse.api.chat.message.payload

import java.util.UUID

data class SendMessagePayload(
    val roomId: UUID,
    val message: String? = null,
)