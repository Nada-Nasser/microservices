package com.learning.notifications.controllers;

import com.learning.clients.notifications.NotificationCreatedResponse;
import com.learning.clients.notifications.NotificationRequest;
import com.learning.notifications.services.NotificationsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notifications")
public class NotificationsController {

    final NotificationsService notificationsService;

    public NotificationsController(NotificationsService notificationsService) {
        this.notificationsService = notificationsService;
    }

    @PostMapping("/create")
    public NotificationCreatedResponse createNotification(@RequestBody NotificationRequest notificationDTO){
        return notificationsService.createNotification(notificationDTO.content(), notificationDTO.customerId());
    }
}
