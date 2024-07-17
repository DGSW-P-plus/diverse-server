package dev.jombi.diverse.business.chat.message.service

import dev.jombi.diverse.business.chat.message.dto.ChatMessageDto
import dev.jombi.diverse.business.chat.message.dto.SendMessageDto
import java.util.UUID

interface ChatMessageService {
    fun getMessages(roomId: UUID): List<ChatMessageDto>
    fun saveMessage(userId: Long, dto: SendMessageDto): ChatMessageDto
    fun sendAndSaveMessage(userId: Long, dto: SendMessageDto)
}