package com.learning.customers.services;

import com.learning.clients.fraud.FraudCheckResponse;
import com.learning.clients.fraud.FraudClient;
import com.learning.clients.notifications.NotificationRequest;
import com.learning.customers.configurations.NotificationConfig;
import com.learning.customers.dtos.CustomerRegistrationRequest;
import com.learning.customers.models.Customer;
import com.learning.customers.repositories.CustomerRepository;
import com.learning.messaging.RabbitMQMessageProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class CustomersService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    private final NotificationConfig notificationConfig;


    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        Customer customer = customerRepository.saveAndFlush(Customer.builder()
                .email(customerRegistrationRequest.email())
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .build());

        log.info("CUSTOMER ID: {}", customer.getId());

        FraudCheckResponse fraudResponse = fraudClient.checkIsFraudster(customer.getId());

        assert fraudResponse != null;
        if(Boolean.TRUE.equals(fraudResponse.isFraudster())){
           throw new IllegalStateException("Fraudster");
        }else{
            rabbitMQMessageProducer.publish(
                    new NotificationRequest(customer.getId() + " : not fraudster customer", customer.getId()),
                    notificationConfig.getInternalNotificationExchange(),
                    notificationConfig.getInternalRoutingKey());
        }
    }
}
