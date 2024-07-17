package dev.jombi.diverse.core.chat.room.repository

import dev.jombi.diverse.core.chat.room.domain.entity.ChatRoomEntity
import dev.jombi.diverse.core.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ChatRoomRepository: JpaRepository<ChatRoomEntity, UUID> {
    fun findAllByMember1OrMember2(member1: Member, member2: Member): List<ChatRoomEntity>
    fun findByMember1AndMember2(member1: Member, member2: Member): ChatRoomEntity?
}