package dev.jombi.diverse.core.chat.message.adapter.impl

import dev.jombi.diverse.business.chat.message.dto.ChatMessageDto
import dev.jombi.diverse.core.chat.message.adapter.TemplateAdapter
import org.springframework.amqp.rabbit.core.RabbitTemplate

class RabbitMQTemplateAdapter(
    private val rabbitTemplate: RabbitTemplate
): TemplateAdapter {
    override fun convertAndSend(dto: ChatMessageDto) {
        rabbitTemplate.convertAndSend("chat.exchange", "room.${dto.roomId}", dto)
    }
}