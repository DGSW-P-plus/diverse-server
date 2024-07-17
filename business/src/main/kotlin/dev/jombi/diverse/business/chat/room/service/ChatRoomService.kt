package dev.jombi.diverse.business.chat.room.service

import dev.jombi.diverse.business.chat.room.dto.ChatRoom
import java.util.UUID

interface ChatRoomService {
    fun createRoom(targetId: Long): UUID
    fun getRoom(roomId: UUID): ChatRoom
    fun getRooms(): List<ChatRoom>
    fun leaveRoom(roomId: UUID)
}