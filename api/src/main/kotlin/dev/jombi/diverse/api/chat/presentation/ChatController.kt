package dev.jombi.diverse.api.chat.presentation

import dev.jombi.diverse.api.chat.dto.request.ChatMessageRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller

@Tag(name = "Chat", description = "채팅")
@Controller
class ChatController(
    private val template: SimpMessagingTemplate
) {
    @Operation(summary = "메시지 전송")
    @MessageMapping("/chat/message")
    fun message(request: ChatMessageRequest) {
        template.convertAndSend("/sub/chat/room/${request.roomId}", request)
    }

    @Operation(summary = "채팅방 입장")
    @MessageMapping("/chat/enter")
    fun enter(request: ChatMessageRequest) {
        template.convertAndSend("/sub/chat/room/${request.roomId}", request)
    }
}