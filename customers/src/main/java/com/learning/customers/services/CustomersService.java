package com.learning.customers.services;

import com.learning.customers.dtos.CustomerRegistrationRequest;
import com.learning.customers.dtos.FraudCheckResponse;
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
    private final RestTemplate restTemplate;

    public CustomersService(CustomerRepository customerRepository, RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
    }

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        Customer customer = customerRepository.saveAndFlush(Customer.builder()
                .email(customerRegistrationRequest.email())
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .build());

        log.info("CUSTOMER ID: {}", customer.getId());

        FraudCheckResponse fraudResponse = restTemplate.getForObject(
                "http://localhost:8091/api/v1/Fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId());

        assert fraudResponse != null;
        if(Boolean.TRUE.equals(fraudResponse.isFraudster())){
            throw new IllegalStateException("Fraudster");
        }
    }
}
