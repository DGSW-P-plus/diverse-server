package dev.jombi.diverse.infra.websocket.config

import dev.jombi.diverse.infra.websocket.MessageBrokerRegistryAdapter
import dev.jombi.diverse.infra.websocket.handler.StompHandler
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.ChannelRegistration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig(
    private val stompHandler: StompHandler,
    private val messageBrokerRegistryAdapter: MessageBrokerRegistryAdapter
) : WebSocketMessageBrokerConfigurer {
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/ws")
            .setAllowedOriginPatterns("*")
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        messageBrokerRegistryAdapter.set(registry)
    }

    override fun configureClientInboundChannel(registration: ChannelRegistration) {
        registration.interceptors(stompHandler)
    }

    override fun configureWebSocketTransport(registry: WebSocketTransportRegistration) {
        registry.setMessageSizeLimit(512 * 1024) // 메시지 최대 크기를 512KB
        registry.setSendTimeLimit(10 * 1000) // 10초 이내에 메시지를 보내야 함
        registry.setSendBufferSizeLimit(512 * 1024) // 버퍼 사이즈를 512KB로 제한
    }
}
