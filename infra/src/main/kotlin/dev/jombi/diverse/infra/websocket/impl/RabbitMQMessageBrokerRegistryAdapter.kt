package dev.jombi.diverse.infra.websocket.impl

import dev.jombi.diverse.infra.rabbitmq.properties.RabbitMQProperties
import dev.jombi.diverse.infra.websocket.MessageBrokerRegistryAdapter
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.util.AntPathMatcher

class RabbitMQMessageBrokerRegistryAdapter(
    private val rabbitMQProperties: RabbitMQProperties
): MessageBrokerRegistryAdapter {
    override fun set(registry: MessageBrokerRegistry) {
        registry.setPathMatcher(AntPathMatcher("."))
        registry.setApplicationDestinationPrefixes("/pub")
        registry.enableStompBrokerRelay("/queue", "/topic", "/exchange", "/amq/queue")
            .setRelayHost(rabbitMQProperties.host)
            .setRelayPort(rabbitMQProperties.port)
            .setVirtualHost("/")
    }
}