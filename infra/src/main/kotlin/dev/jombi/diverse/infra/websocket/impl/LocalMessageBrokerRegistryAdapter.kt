package dev.jombi.diverse.infra.websocket.impl

import dev.jombi.diverse.infra.websocket.MessageBrokerRegistryAdapter
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.stereotype.Component

@Component
class LocalMessageBrokerRegistryAdapter: MessageBrokerRegistryAdapter {
    override fun set(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/sub")
        registry.setApplicationDestinationPrefixes("/pub")
    }
}