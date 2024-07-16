package dev.jombi.diverse.infra.websocket.interceptor

import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.http.server.ServletServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.HandshakeInterceptor
import java.lang.Exception

@Component
class HttpHandshakeInterceptor: HandshakeInterceptor {
    override fun beforeHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        handler: WebSocketHandler,
        attributes: MutableMap<String, Any>
    ): Boolean {
        val req = (request as ServletServerHttpRequest).servletRequest
        val res = (response as ServletServerHttpResponse).servletResponse

        val accessToken = TODO()

        return true
    }

    override fun afterHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        handler: WebSocketHandler,
        exception: Exception?
    ) {
        // Do nothing
    }
}