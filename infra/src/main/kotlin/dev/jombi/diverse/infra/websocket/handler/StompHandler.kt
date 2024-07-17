package dev.jombi.diverse.infra.websocket.handler

import dev.jombi.diverse.core.member.MemberHolder
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.SimpAttributesContextHolder
import org.springframework.messaging.simp.SimpMessageType
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.messaging.support.MessageBuilder
import org.springframework.messaging.support.MessageHeaderAccessor
import org.springframework.stereotype.Component

@Component
class StompHandler(private val memberHolder: MemberHolder) : ChannelInterceptor {
    override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {
        val accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor::class.java)!!

        when (accessor.messageType) {
            SimpMessageType.CONNECT -> {
                val member = memberHolder.get()

                SimpAttributesContextHolder.currentAttributes().setAttribute("userId", member.id)

                return MessageBuilder.createMessage(message.payload, accessor.messageHeaders)
            }
            SimpMessageType.CONNECT_ACK,
            SimpMessageType.MESSAGE,
            SimpMessageType.SUBSCRIBE -> {
                if (accessor.destination != null) {
                    val simpAttributes = SimpAttributesContextHolder.currentAttributes()
                    val userId = simpAttributes.getAttribute("userId") as String

//                    messageService.sub(
//                        userId = userId.toLong(),
//                        roomId = accessor.destination?.substringAfterLast(".").toString()
//                    )
                }
            }

            SimpMessageType.UNSUBSCRIBE -> {
                val simpAttributes = SimpAttributesContextHolder.currentAttributes()
                val userId = simpAttributes.getAttribute("userId") as String

//                messageService.unSub(
//                    userId = userId.toLong()
//                )
            }

            else -> {}
        }

        return message
    }
}