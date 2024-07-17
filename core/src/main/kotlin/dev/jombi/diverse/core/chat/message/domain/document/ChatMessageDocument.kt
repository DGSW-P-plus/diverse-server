package dev.jombi.diverse.core.chat.message.domain.document

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(collection = "chat_messages")
class ChatMessageDocument(
    @Id
    val id: ObjectId? = null,

    val roomId: UUID,
    val message: String = "",
    val userId: Long,
)