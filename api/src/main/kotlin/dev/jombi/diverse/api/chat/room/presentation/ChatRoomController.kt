package dev.jombi.diverse.api.chat.room.presentation

import dev.jombi.diverse.api.chat.room.dto.request.CreateChatRoomRequest
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
    fun createRoom(@RequestBody request: CreateChatRoomRequest) = ResponseData.created(data = chatRoomService.createRoom(request.targetId), message = "채팅 방 생성 완료")

    @GetMapping
    fun getRooms() = ResponseData.ok(data = chatRoomService.getRooms(), message = "채팅방 불러오기 완료")

    @GetMapping("/{roomId}")
    fun getRoom(@PathVariable roomId: UUID) = ResponseData.ok(data = chatRoomService.getRoom(roomId), message = "채팅방 불러오기 성공")
}