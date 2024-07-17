package dev.jombi.diverse.core.chat.message.repository

import dev.jombi.diverse.core.chat.message.domain.document.ChatMessageDocument
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface ChatMessageRepository: MongoRepository<ChatMessageDocument, Long> {
    fun findByRoomId(roomId: UUID): List<ChatMessageDocument>
}