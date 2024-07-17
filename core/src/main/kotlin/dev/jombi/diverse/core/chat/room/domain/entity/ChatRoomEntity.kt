package dev.jombi.diverse.core.chat.room.domain.entity

import dev.jombi.diverse.core.member.entity.Member
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "chat_rooms")
class ChatRoomEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member1_id", nullable = false)
    val member1: Member,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member2_id", nullable = false)
    val member2: Member
)