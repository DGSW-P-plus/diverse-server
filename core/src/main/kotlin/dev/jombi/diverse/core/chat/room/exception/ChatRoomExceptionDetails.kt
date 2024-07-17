package dev.jombi.diverse.core.chat.room.exception

import dev.jombi.diverse.common.exception.ExceptionDetail
import org.springframework.http.HttpStatus

enum class ChatRoomExceptionDetails(override val message: String, override val status: HttpStatus): ExceptionDetail {
    CHAT_ROOM_NOT_FOUND("'%s' 사용자를 찾을 수 없음", HttpStatus.NOT_FOUND),
    CHAT_ROOM_NOT_ALLOWED("'%s' 채팅에 접근 할 수 없음", HttpStatus.BAD_REQUEST)
    ;

    override val code = name
}