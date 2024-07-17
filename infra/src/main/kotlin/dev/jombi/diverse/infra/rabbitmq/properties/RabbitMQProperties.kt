package dev.jombi.diverse.infra.rabbitmq.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.rabbitmq")
data class RabbitMQProperties(
    val host: String,
    val port: Int,
    val username: String,
    val password: String
)