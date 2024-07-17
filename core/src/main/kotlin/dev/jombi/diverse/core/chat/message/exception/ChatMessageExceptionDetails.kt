package dev.jombi.diverse.core.chat.message.exception

import dev.jombi.diverse.common.exception.ExceptionDetail
import org.springframework.http.HttpStatus

enum class ChatMessageExceptionDetails(override val message: String, override val status: HttpStatus): ExceptionDetail {
    CHAT_MESSAGE_EMPTY("메세지가 비어 있음", HttpStatus.BAD_REQUEST)
    ;

    override val code = name
}