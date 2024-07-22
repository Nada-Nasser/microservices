package com.learning.fraud.dtos;

import lombok.Builder;

@Builder
public record FraudCheckResponse(Boolean isFraudster) {
}
