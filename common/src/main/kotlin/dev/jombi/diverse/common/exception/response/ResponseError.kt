package dev.jombi.diverse.common.exception.response

import dev.jombi.diverse.common.exception.ExceptionDetail
import dev.jombi.diverse.common.response.ResponseEmpty
import org.springframework.http.ResponseEntity

class ResponseError(code: String, status: Int, val detail: String) : ResponseEmpty(code, status) {
    companion object {
        fun of(message: ExceptionDetail, vararg args: Any?) =
            ResponseEntity
                .status(message.status)
                .body(ResponseError(message.code, message.status.value(), message.message.format(*args)))
    }
}
