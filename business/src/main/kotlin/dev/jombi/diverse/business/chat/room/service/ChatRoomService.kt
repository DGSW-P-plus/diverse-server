package dev.jombi.diverse.business.chat.room.service

import dev.jombi.diverse.business.chat.message.dto.ChatMessageDto
import dev.jombi.diverse.business.chat.room.dto.ChatRoomDto
import java.util.UUID

interface ChatRoomService {
    fun createRoom(targetId: Long): UUID
    fun getRoom(roomId: UUID): ChatRoomDto
    fun getRooms(): List<ChatRoomDto>
}