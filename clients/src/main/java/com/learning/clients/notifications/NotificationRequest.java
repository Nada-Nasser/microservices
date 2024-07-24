package com.learning.clients.notifications;
import lombok.Builder;

@Builder
public record NotificationRequest(String content, Integer customerId) {
}
