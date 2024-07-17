package dev.jombi.diverse.business.chat.room.dto

data class ChatRoomMemberResponse(
    val id: Long,
    val username: String,
    val nickname: String,
)