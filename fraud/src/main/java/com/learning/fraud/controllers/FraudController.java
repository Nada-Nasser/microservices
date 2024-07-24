package com.learning.fraud.controllers;

import com.learning.clients.fraud.FraudCheckResponse;
import com.learning.fraud.services.FraudService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/Fraud-check")
public record FraudController(FraudService fraudService) {

    @GetMapping("{customerId}")
    public FraudCheckResponse checkIsFraudster(@PathVariable("customerId") Integer customerId){
        Boolean flag = fraudService.isFraudster(customerId);
        return new FraudCheckResponse(flag);
    }
}
