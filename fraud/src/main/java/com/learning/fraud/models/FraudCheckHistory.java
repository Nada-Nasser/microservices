package com.learning.fraud.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fraud_check_history")
public class FraudCheckHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fraud_check_history_seq")
    @SequenceGenerator(name = "fraud_check_history_seq")
    @Column(name = "id", nullable = false)
    private Integer id;

    private Integer customerId;
    private Boolean isFraudster;
    private LocalDateTime createdAt;
}