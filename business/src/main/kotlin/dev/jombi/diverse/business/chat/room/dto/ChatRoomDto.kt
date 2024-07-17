package dev.jombi.diverse.business.chat.room.dto

import java.util.UUID

data class ChatRoomDto(
    val id: UUID,
    val member1: Long,
    val member2: Long,
    val lastMessage: String
)