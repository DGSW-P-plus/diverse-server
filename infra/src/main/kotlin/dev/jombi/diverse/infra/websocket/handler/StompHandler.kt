package dev.jombi.diverse.infra.websocket.handler

import dev.jombi.diverse.core.member.domain.details.MemberDetails
import dev.jombi.diverse.infra.security.jwt.JwtAuthToken
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.SimpAttributesContextHolder
import org.springframework.messaging.simp.SimpMessageType
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.messaging.support.MessageBuilder
import org.springframework.messaging.support.MessageHeaderAccessor
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.stereotype.Component

@Component
class StompHandler(
    private val authManager: AuthenticationManager,
) : ChannelInterceptor {
    override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {
        val accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor::class.java)!!


        when (accessor.messageType) {
            SimpMessageType.CONNECT -> {
                val token = accessor.getFirstNativeHeader("Authorization")?.removePrefix("Bearer ") ?: return null
                val auth = authManager.authenticate(JwtAuthToken(token))
                val member = (auth.principal as MemberDetails).member

                SimpAttributesContextHolder.currentAttributes().setAttribute("userId", member.id.id)

                return MessageBuilder.createMessage(message.payload, accessor.messageHeaders)
            }
            else -> {}
        }

        return message
    }
}
