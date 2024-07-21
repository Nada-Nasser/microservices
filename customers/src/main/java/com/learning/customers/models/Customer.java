package com.learning.customers.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String email;
    private String firstName;
    private String lastName;
}