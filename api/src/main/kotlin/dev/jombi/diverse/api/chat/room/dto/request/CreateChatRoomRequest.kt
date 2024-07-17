package dev.jombi.diverse.api.chat.room.dto.request

import com.fasterxml.jackson.annotation.JsonCreator

data class CreateChatRoomRequest @JsonCreator constructor(
    val targetId: Long
)