package dev.jombi.diverse.core.chat.room.mapper

import dev.jombi.diverse.business.common.Mapper
import dev.jombi.diverse.common.exception.CustomException
import dev.jombi.diverse.core.chat.room.domain.entity.ChatRoomEntity
import dev.jombi.diverse.business.chat.room.dto.ChatRoomDto
import dev.jombi.diverse.core.chat.message.repository.ChatMessageRepository
import dev.jombi.diverse.core.member.exception.MemberExceptionDetails
import dev.jombi.diverse.core.member.repository.MemberJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ChatRoomMapper(
    private val memberJpaRepository: MemberJpaRepository,
    private val chatMessageRepository: ChatMessageRepository
): Mapper<ChatRoomDto, ChatRoomEntity> {
    override fun toDomain(entity: ChatRoomEntity) = ChatRoomDto(
        id = entity.id!!,
        member1 = entity.member1.id.id,
        member2 = entity.member2.id.id,
        lastMessage = chatMessageRepository.findAllByRoomId(entity.id).sortedWith { o1, o2 -> o1.createdAt!!.compareTo(o2.createdAt) }
            .last().message
    )

    override fun toEntity(domain: ChatRoomDto) = ChatRoomEntity(
        id = domain.id,
        member1 = memberJpaRepository.findByIdOrNull(domain.member1) ?: throw CustomException(MemberExceptionDetails.MEMBER_NOT_FOUND),
        member2 = memberJpaRepository.findByIdOrNull(domain.member2) ?: throw CustomException(MemberExceptionDetails.MEMBER_NOT_FOUND)
    )
}