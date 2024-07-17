package dev.jombi.diverse.infra.rabbitmq.config

import dev.jombi.diverse.infra.rabbitmq.properties.RabbitMQProperties
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableRabbit
class RabbitMQConfig(
    private val rabbitMQProperties: RabbitMQProperties
) : ApplicationListener<ApplicationReadyEvent> {
    companion object {
        const val CHAT_QUEUE_NAME = "chat.queue"
        const val CHAT_EXCHANGE_NAME = "chat.exchange"
        const val ROUTING_KEY = "*.room.*"
    }

    @Bean
    fun queue() = Queue(CHAT_QUEUE_NAME, true)

    @Bean
    fun exchange() = TopicExchange(CHAT_EXCHANGE_NAME)

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange) = BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY)

    @Bean
    fun rabbitTemplate() = RabbitTemplate(connectionFactory()).apply {
        setExchange(CHAT_EXCHANGE_NAME)
        routingKey = ROUTING_KEY
    }

    @Bean
    fun rabbitAdmin(): RabbitAdmin = RabbitAdmin(connectionFactory()).apply {
        declareExchange(exchange())
        declareQueue(queue())
        declareBinding(binding(queue(), exchange()))
    }

    @Bean
    fun connectionFactory() = CachingConnectionFactory().apply {
        setHost(rabbitMQProperties.host)
        port = rabbitMQProperties.port
        username = rabbitMQProperties.username
        setPassword(rabbitMQProperties.password)
        virtualHost = "/"
    }

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        rabbitAdmin().initialize()
    }
}
