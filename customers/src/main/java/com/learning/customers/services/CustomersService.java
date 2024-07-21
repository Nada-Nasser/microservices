package com.learning.customers.services;

import com.learning.customers.dtos.CustomerRegistrationRequest;
import com.learning.customers.models.Customer;
import com.learning.customers.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomersService(CustomerRepository customerRepository) {

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        Customer customer = Customer.builder()
                .email(customerRegistrationRequest.email())
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .build();

        customerRepository.save(customer);
    }
}
