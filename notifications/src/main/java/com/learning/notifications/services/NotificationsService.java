package com.learning.notifications.services;

import com.learning.clients.notifications.NotificationCreatedResponse;
import com.learning.clients.notifications.NotificationRequest;
import com.learning.notifications.models.Notification;
import com.learning.notifications.repositries.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationsService {

    private final NotificationRepository notificationRepository;

    public NotificationsService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public NotificationCreatedResponse createNotification(NotificationRequest notificationRequest) {
        LocalDateTime date = LocalDateTime.now();
        notificationRepository.save(Notification.builder()
                .content(notificationRequest.content())
                        .customerId(notificationRequest.customerId())
                .dateTime(date)
                .build());

        return new NotificationCreatedResponse(date.toString());
    }
}
