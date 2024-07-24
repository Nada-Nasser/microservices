package com.learning.clients.notifications;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NOTIFICATIONS")
public interface NotificationsClient {
    @PostMapping("api/v1/notifications/create")
    NotificationCreatedResponse createNotification(@RequestBody NotificationRequest notificationRequest);
}
