package dev.jombi.diverse.core.chat.message.adapter.impl

import com.fasterxml.jackson.databind.ObjectMapper
import dev.jombi.diverse.business.chat.message.dto.ChatMessageDto
import dev.jombi.diverse.core.chat.message.adapter.TemplateAdapter
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class RabbitMQTemplateAdapter(
    private val rabbitTemplate: RabbitTemplate,
    private val objectMapper: ObjectMapper
): TemplateAdapter {
    override fun convertAndSend(dto: ChatMessageDto) {
        rabbitTemplate.convertAndSend("chat.exchange", "room.${dto.roomId}", objectMapper.writeValueAsBytes(dto))
    }
}
