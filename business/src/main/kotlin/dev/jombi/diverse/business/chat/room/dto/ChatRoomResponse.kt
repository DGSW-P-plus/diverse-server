package dev.jombi.diverse.business.chat.room.dto

import java.util.UUID

data class ChatRoomResponse(
    val id: UUID,
    val recipient: ChatRoomMemberResponse,
    val lastMessage: String
)