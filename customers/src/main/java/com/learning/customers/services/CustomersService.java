package com.learning.customers.services;

import com.learning.clients.fraud.FraudCheckResponse;
import com.learning.clients.fraud.FraudClient;
import com.learning.clients.notifications.NotificationRequest;
import com.learning.clients.notifications.NotificationsClient;
import com.learning.customers.dtos.CustomerRegistrationRequest;
import com.learning.customers.models.Customer;
import com.learning.customers.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class CustomersService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationsClient notificationsClient;

    public CustomersService(CustomerRepository customerRepository, FraudClient fraudClient, NotificationsClient notificationsClient) {
        this.customerRepository = customerRepository;
        this.fraudClient = fraudClient;
        this.notificationsClient = notificationsClient;
    }

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
            notificationsClient.createNotification(new NotificationRequest(customer.getId() + " : Fraudster customer",customer.getId()));
            throw new IllegalStateException("Fraudster");
        }else{
            notificationsClient.createNotification(new NotificationRequest(customer.getId() + " : not fraudster customer", customer.getId()));
        }
    }
}
