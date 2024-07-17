package dev.jombi.diverse.infra.rabbitmq.config

import dev.jombi.diverse.infra.rabbitmq.properties.RabbitMQProperties
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


//@Configuration
//@EnableRabbit
//class RabbitMQConfig(
//    private val rabbitMQProperties: RabbitMQProperties
//) {
//    companion object {
//        const val CHAT_QUEUE_NAME = "chat.queue"
//        const val CHAT_EXCHANGE_NAME = "chat.exchange"
//        const val ROUTING_KEY = "*.room.*"
//    }
//
//    @Bean
//    fun queue() = Queue(CHAT_QUEUE_NAME, true)
//
//    @Bean
//    fun exchange() = TopicExchange(CHAT_EXCHANGE_NAME)
//
//    @Bean
//    fun binding(queue: Queue, exchange: TopicExchange) = BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY)
//
//    @Bean
//    fun rabbitTemplate() = RabbitTemplate(connectionFactory()).apply {
//        messageConverter = jsonMessageConverter()
//    }
//
//    @Bean
//    fun connectionFactory() = CachingConnectionFactory().apply {
//        setHost(rabbitMQProperties.host)
//        port = rabbitMQProperties.port
//        username = rabbitMQProperties.username
//        setPassword(rabbitMQProperties.password)
//        virtualHost = "/"
//    }
//
//    @Bean
//    fun jsonMessageConverter() = Jackson2JsonMessageConverter()
//}
