package dev.jombi.diverse.api.chat.message.presentation

import dev.jombi.diverse.api.chat.message.payload.SendMessagePayload
import dev.jombi.diverse.business.chat.message.dto.SendMessageDto
import dev.jombi.diverse.business.chat.message.service.ChatMessageService
import dev.jombi.diverse.common.response.ResponseData
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpAttributesContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/chat/messages")
class ChatMessageController(
    private val chatMessageService: ChatMessageService
) {
    @GetMapping
    fun getMessages(@RequestParam roomId: UUID) = ResponseData.ok(data = chatMessageService.getMessages(roomId))

    @MessageMapping("/chat/sendMessage")
    fun sendMessage(@Payload payload: SendMessagePayload) {
        val userId = SimpAttributesContextHolder.currentAttributes().getAttribute("userId") as Long

        chatMessageService.sendAndSaveMessage(userId, SendMessageDto(
            roomId = payload.roomId,
            message = payload.message
        ))
    }
}