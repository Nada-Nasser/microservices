package com.learning.customers.configurations;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class NotificationConfig {

    @Value("${rabbitmq.exchanges[0].internal}")
    private String internalNotificationExchange;

    @Value("${rabbitmq.queues[0].notifications}")
    private String internalNotificationQueue;

    @Value("${rabbitmq.routing-keys[0].internal-notification}")
    private String internalRoutingKey;

    @Bean
    public TopicExchange internalNotificationTopicExchange(){
        return new TopicExchange(internalNotificationExchange);
    }

    @Bean
    public Queue internalNotificationQueue(){
       return new Queue(internalNotificationQueue);
    }

    @Bean
    public Binding internalNotificationBinding(){
        return BindingBuilder
                .bind(internalNotificationQueue())
                .to(internalNotificationTopicExchange())
                .with(internalRoutingKey);
    }
}
