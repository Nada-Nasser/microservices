package com.learning.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "FRAUD"
)
public interface FraudClient {
    @GetMapping("api/v1/Fraud-check/{customerId}")
    FraudCheckResponse checkIsFraudster(@PathVariable("customerId") Integer customerId);
}
