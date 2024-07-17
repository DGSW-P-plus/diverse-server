package dev.jombi.diverse.infra.websocket

import org.springframework.messaging.simp.config.MessageBrokerRegistry

interface MessageBrokerRegistryAdapter {
    fun set(registry: MessageBrokerRegistry)
}