package dev.jombi.diverse.infra.websocket.handler

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.stereotype.Component

@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@Component
class StompHandler: ChannelInterceptor {
    override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {
        val accessor = StompHeaderAccessor.wrap(message)
        val command = accessor.command

        if (command != null) {
            when (command) {
                StompCommand.CONNECT -> {
                    println("CONNECT")
                }
                StompCommand.ERROR -> {
                    println("ERROR")
                }
                else -> {}
            }
        }

        return super.preSend(message, channel)
    }
}