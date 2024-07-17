package dev.jombi.diverse.core.chat.message.service

import dev.jombi.diverse.business.chat.message.dto.ChatMessageDto
import dev.jombi.diverse.business.chat.message.dto.SendMessageDto
import dev.jombi.diverse.business.chat.message.service.ChatMessageService
import dev.jombi.diverse.common.exception.CustomException
import dev.jombi.diverse.core.chat.message.adapter.TemplateAdapter
import dev.jombi.diverse.core.chat.message.domain.document.ChatMessageDocument
import dev.jombi.diverse.core.chat.message.exception.ChatMessageExceptionDetails
import dev.jombi.diverse.core.chat.message.repository.ChatMessageRepository
import dev.jombi.diverse.core.chat.room.exception.ChatRoomExceptionDetails
import dev.jombi.diverse.core.chat.room.repository.ChatRoomRepository
import dev.jombi.diverse.core.member.MemberHolder
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class ChatMessageServiceImpl(
    private val chatMessageRepository: ChatMessageRepository,
    private val memberHolder: MemberHolder,
    private val chatRoomRepository: ChatRoomRepository,
    private val templateAdapter: TemplateAdapter
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

        val saved = chatMessageRepository.save(message)
        return ChatMessageDto(
            roomId = saved.roomId,
            message = saved.message,
            userId = saved.userId
        )
    }

    override fun sendAndSaveMessage(userId: Long, dto: SendMessageDto) {
        if (dto.message == null || dto.message!!.isBlank()) {
            throw CustomException(ChatMessageExceptionDetails.CHAT_MESSAGE_EMPTY)
        }

        val message = saveMessage(userId, dto)

        templateAdapter.convertAndSend(message)
    }
}
