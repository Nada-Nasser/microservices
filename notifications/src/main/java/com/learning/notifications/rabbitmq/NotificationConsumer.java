package com.learning.notifications.rabbitmq;

import com.learning.clients.notifications.NotificationRequest;
import com.learning.notifications.services.NotificationsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class NotificationConsumer {

    private final NotificationsService notificationsService;

    @RabbitListener(queues = "${rabbitmq.queues[0].notifications}")
    public void consume(NotificationRequest notificationRequest){
        log.info("consumed {} from queue", notificationRequest);
        notificationsService.createNotification(notificationRequest);
    }
}
