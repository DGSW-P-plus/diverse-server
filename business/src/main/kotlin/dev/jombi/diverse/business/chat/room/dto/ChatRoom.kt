package dev.jombi.diverse.business.chat.room.dto

import java.util.UUID

data class ChatRoom(
    val id: UUID,
    val member1: Long,
    val member2: Long
)