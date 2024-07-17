package dev.jombi.diverse.infra.websocket.impl

import dev.jombi.diverse.infra.websocket.MessageBrokerRegistryAdapter
import org.springframework.messaging.simp.config.MessageBrokerRegistry

class LocalMessageBrokerRegistryAdapter: MessageBrokerRegistryAdapter {
    override fun set(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/sub")
        registry.setApplicationDestinationPrefixes("/pub")
    }
}
