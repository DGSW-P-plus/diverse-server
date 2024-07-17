package dev.jombi.diverse.business.chat.room.service

import dev.jombi.diverse.business.chat.room.dto.ChatRoomDto
import dev.jombi.diverse.business.chat.room.dto.ChatRoomResponse
import java.util.UUID

interface ChatRoomService {
    fun createRoom(targetId: Long): UUID
    fun getRoom(roomId: UUID): ChatRoomDto
    fun getRooms(): List<ChatRoomResponse>
}