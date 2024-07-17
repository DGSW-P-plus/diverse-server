package dev.jombi.diverse.core.chat.message.adapter

import dev.jombi.diverse.business.chat.message.dto.ChatMessageDto

interface TemplateAdapter {
    fun convertAndSend(dto: ChatMessageDto)
}