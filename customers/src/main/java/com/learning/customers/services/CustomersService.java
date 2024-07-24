package com.learning.customers.services;

import com.learning.clients.fraud.FraudCheckResponse;
import com.learning.clients.fraud.FraudClient;
import com.learning.customers.dtos.CustomerRegistrationRequest;
import com.learning.customers.models.Customer;
import com.learning.customers.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class CustomersService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;

    public CustomersService(CustomerRepository customerRepository,FraudClient fraudClient) {
        this.customerRepository = customerRepository;
        this.fraudClient = fraudClient;
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
            throw new IllegalStateException("Fraudster");
        }
    }
}
