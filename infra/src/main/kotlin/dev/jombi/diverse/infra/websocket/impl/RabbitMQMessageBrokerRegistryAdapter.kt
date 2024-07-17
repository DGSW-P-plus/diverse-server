package dev.jombi.diverse.infra.websocket.impl

import dev.jombi.diverse.infra.websocket.MessageBrokerRegistryAdapter
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher

@Component
class RabbitMQMessageBrokerRegistryAdapter: MessageBrokerRegistryAdapter {
    override fun set(registry: MessageBrokerRegistry) {
        registry.setPathMatcher(AntPathMatcher("."))
        registry.setApplicationDestinationPrefixes("/pub")
        registry.enableStompBrokerRelay("/queue", "/topic", "/exchange", "/amq/queue")
    }
}
