package dev.jombi.diverse.core.chat.message.service

import dev.jombi.diverse.business.chat.message.dto.ChatMessageDto
import dev.jombi.diverse.business.chat.message.dto.SendMessageDto
import dev.jombi.diverse.business.chat.message.service.ChatMessageService
import dev.jombi.diverse.common.exception.CustomException
import dev.jombi.diverse.core.chat.message.domain.document.ChatMessageDocument
import dev.jombi.diverse.core.chat.message.repository.ChatMessageRepository
import dev.jombi.diverse.core.chat.room.exception.ChatRoomExceptionDetails
import dev.jombi.diverse.core.chat.room.repository.ChatRoomRepository
import dev.jombi.diverse.core.member.MemberHolder
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class ChatMessageServiceImpl(
    private val rabbitTemplate: RabbitTemplate,
    private val chatMessageRepository: ChatMessageRepository,
    private val memberHolder: MemberHolder,
    private val chatRoomRepository: ChatRoomRepository,
//    private val template: SimpMessageSendingOperations
) : ChatMessageService {
    @Transactional(readOnly = true)
    override fun getMessages(roomId: UUID): List<ChatMessageDto> {
        val member = memberHolder.get()

        val room = chatRoomRepository.findByIdOrNull(roomId) ?: throw CustomException(ChatRoomExceptionDetails.CHAT_ROOM_NOT_FOUND)

        if (room.member1.id != member.id && room.member2.id != member.id) throw CustomException(ChatRoomExceptionDetails.CHAT_ROOM_NOT_ALLOWED)

        val messages = chatMessageRepository.findByRoomId(room.id!!)

        return messages.map { ChatMessageDto(it.roomId, it.message, it.userId) }
    }

    override fun saveMessage(userId: Long, dto: SendMessageDto): ChatMessageDto {
        val message = ChatMessageDocument(
            roomId = dto.roomId,
            message = dto.message ?: "",
            userId = userId
        )

        println("MONGO 1")

        val saved = chatMessageRepository.save(message)

        println("MONGO 2")

        return ChatMessageDto(
            roomId = saved.roomId,
            message = saved.message,
            userId = saved.userId
        )
    }

    override fun sendAndSaveMessage(userId: Long, dto: SendMessageDto) {
        rabbitTemplate.convertAndSend("chat.exchange", "room.${dto.roomId}", saveMessage(userId, dto))
//        template.convertAndSend("/sub/chat/rooms/${dto.roomId}", saveMessage(userId, dto))
    }

//    override fun sendEventMessage(message: MessageEventDto, roomId: String) {
//        rabbitTemplate.convertAndSend(
//            "chat.exchange", "room.${roomId}", message
//        )
//    }

    override fun subscribe(userId: Long, roomId: UUID) {
//        if (roomId != "message" && roomId.length == 24) {
//
//            sendEventMessage(
//                message = MessageEventDto(
//                    userId = userId,
//                    type = Type.SUB
//                ),
//                roomId = roomId
//            )
//            roomInfoRepository.save(
//                RoomInfoEntity(
//                    userId = userId,
//                    roomId = roomId
//                )
//            )
//        }
    }

    override fun unsubscribe(userId: Long) {
//        roomInfoRepository.deleteById(userId)
    }
}