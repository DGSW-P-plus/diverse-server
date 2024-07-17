package dev.jombi.diverse.core.chat.message.adapter.impl

import dev.jombi.diverse.business.chat.message.dto.ChatMessageDto
import dev.jombi.diverse.core.chat.message.adapter.TemplateAdapter
import org.springframework.messaging.simp.SimpMessageSendingOperations

class LocalTemplateAdapter(
    private val sendingOperations: SimpMessageSendingOperations
): TemplateAdapter {
    override fun convertAndSend(dto: ChatMessageDto) {
        sendingOperations.convertAndSend("/sub/chat/rooms/${dto.roomId}", dto)
    }
}
