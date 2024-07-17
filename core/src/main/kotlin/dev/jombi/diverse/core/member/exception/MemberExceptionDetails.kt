package dev.jombi.diverse.core.member.exception

import dev.jombi.diverse.common.exception.ExceptionDetail
import org.springframework.http.HttpStatus

enum class MemberExceptionDetails(override val message: String, override val status: HttpStatus): ExceptionDetail {
    MEMBER_NOT_FOUND("'%s' 멤버를 찾을 수 없음", HttpStatus.NOT_FOUND),
    ;

    override val code = name
}