package com.learning.customers.dtos;

import lombok.Builder;

@Builder
public record FraudCheckResponse(Boolean isFraudster) {
}
