package dev.jombi.diverse.api.chat.dto.request

import com.fasterxml.jackson.annotation.JsonCreator

data class CreateRoomRequest @JsonCreator constructor(
    val targetId: Long
)