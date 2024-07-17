package dev.jombi.diverse.api.chat.room.presentation

import dev.jombi.diverse.api.chat.room.dto.request.CreateChatRoomRequest
import dev.jombi.diverse.business.chat.message.dto.ChatMessageDto
import dev.jombi.diverse.business.chat.room.service.ChatRoomService
import dev.jombi.diverse.common.response.ResponseData
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/chat/rooms")
class ChatRoomController(
    private val chatRoomService: ChatRoomService
) {
    @PostMapping
    fun createRoom(@RequestBody request: CreateChatRoomRequest) = ResponseData.created(data = chatRoomService.createRoom(request.targetId))

    @GetMapping
    fun getRooms() = ResponseData.ok(data = chatRoomService.getRooms())

    @GetMapping("/{roomId}")
    fun getRoom(@PathVariable roomId: UUID) = ResponseData.ok(data = chatRoomService.getRoom(roomId))
}