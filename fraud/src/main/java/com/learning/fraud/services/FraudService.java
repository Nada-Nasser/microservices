package com.learning.fraud.services;

import com.learning.fraud.models.FraudCheckHistory;
import com.learning.fraud.repositories.FraudCheckHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {

    public Boolean isFraudster(Integer customerId){
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .customerId(customerId)
                        .build()
        );

        return false;
    }
}
