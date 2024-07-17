package dev.jombi.diverse.core.chat.room.service

import dev.jombi.diverse.business.chat.room.service.ChatRoomService
import dev.jombi.diverse.common.exception.CustomException
import dev.jombi.diverse.business.chat.room.dto.ChatRoomDto
import dev.jombi.diverse.business.chat.room.dto.ChatRoomMemberResponse
import dev.jombi.diverse.business.chat.room.dto.ChatRoomResponse
import dev.jombi.diverse.core.chat.room.domain.entity.ChatRoomEntity
import dev.jombi.diverse.core.chat.room.exception.ChatRoomExceptionDetails
import dev.jombi.diverse.core.chat.room.mapper.ChatRoomMapper
import dev.jombi.diverse.core.chat.room.repository.ChatRoomRepository
import dev.jombi.diverse.core.member.MemberHolder
import dev.jombi.diverse.core.member.exception.MemberExceptionDetails
import dev.jombi.diverse.core.member.repository.MemberJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class ChatRoomServiceImpl(
    private val memberHolder: MemberHolder,
    private val chatRoomRepository: ChatRoomRepository,
    private val chatRoomMapper: ChatRoomMapper,
    private val memberJpaRepository: MemberJpaRepository,
): ChatRoomService {
    @Transactional(readOnly = true)
    override fun getRooms(): List<ChatRoomResponse> {
        val member = memberHolder.get()

        val rooms = chatRoomRepository.findAllByMember1OrMember2(member, member)

        return rooms.map { chatRoomMapper.toDomain(it) }.map {
            val recipientId = if (it.member1 == member.id.id) it.member2 else it.member1
            val recipient = memberJpaRepository.findByIdOrNull(recipientId) ?: throw CustomException(MemberExceptionDetails.MEMBER_NOT_FOUND)
            val recipientDto = ChatRoomMemberResponse(
                id = recipient.id.id,
                username = recipient.username,
                nickname = recipient.nickname,
            )

            ChatRoomResponse(
                id =  it.id,
                recipient = recipientDto,
                lastMessage = it.lastMessage
            )
        }
    }

    @Transactional(readOnly = true)
    override fun getRoom(roomId: UUID): ChatRoomDto {
        val member = memberHolder.get()

        val room = chatRoomRepository.findByIdOrNull(roomId) ?: throw CustomException(ChatRoomExceptionDetails.CHAT_ROOM_NOT_FOUND)

        if (room.member1.id != member.id && room.member2.id != member.id) throw CustomException(ChatRoomExceptionDetails.CHAT_ROOM_NOT_ALLOWED)

        return chatRoomMapper.toDomain(room)
    }

    override fun createRoom(targetId: Long): UUID {
        val member = memberHolder.get()

        val target = memberJpaRepository.findByIdOrNull(targetId) ?: throw CustomException(MemberExceptionDetails.MEMBER_NOT_FOUND)

        val room1 = chatRoomRepository.findByMember1AndMember2(member, target)

        if (room1 != null) {
            return room1.id!!
        }

        val room2 = chatRoomRepository.findByMember1AndMember2(target, member)

        if (room2 != null) {
            return room2.id!!
        }

        val room = chatRoomRepository.save(
            ChatRoomEntity(
                member1 = member,
                member2 = target
            )
        )

        // TODO("send enter message")

        return room.id!!
    }
}