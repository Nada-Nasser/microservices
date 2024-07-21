package com.learning.customers.dtos;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {
}
