package dev.jombi.diverse.infra.websocket.handler

import dev.jombi.diverse.business.chat.message.service.ChatMessageService
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
import java.util.UUID

@Component
class StompHandler(
    private val authManager: AuthenticationManager,
) : ChannelInterceptor {
    override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {
        val accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor::class.java)!!

        println("TEST 1")
        println(accessor.messageType)

        when (accessor.messageType) {
            SimpMessageType.CONNECT -> {
                val token = accessor.getFirstNativeHeader("Authorization")
                    ?: return null
                val auth = authManager.authenticate(JwtAuthToken(token))
                val member = (auth.principal as MemberDetails).member

                SimpAttributesContextHolder.currentAttributes().setAttribute("userId", member.id.id)

                println("TEST 6")

                return MessageBuilder.createMessage(message.payload, accessor.messageHeaders)
            }
//            SimpMessageType.CONNECT_ACK,
//            SimpMessageType.MESSAGE,
//            SimpMessageType.SUBSCRIBE -> {
//                if (accessor.destination != null) {
//                    val simpAttributes = SimpAttributesContextHolder.currentAttributes()
//                    val userId = simpAttributes.getAttribute("userId") as Long
//
//                    chatMessageService.subscribe(
//                        userId = userId,
//                        roomId = UUID.fromString(accessor.destination?.substringAfterLast("."))
//                    )
//                }
//            }
//
//            SimpMessageType.UNSUBSCRIBE -> {
//                val simpAttributes = SimpAttributesContextHolder.currentAttributes()
//                val userId = simpAttributes.getAttribute("userId") as Long
//
//                chatMessageService.unsubscribe(
//                    userId = userId
//                )
//            }

            else -> {}
        }

        return message
    }
}