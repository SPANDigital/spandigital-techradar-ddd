package com.spandigital.ddd.freelancer.infrastructure.rest;

import com.spandigital.ddd.freelancer.domain.Customer;
import com.spandigital.ddd.freelancer.domain.CustomerId;
import com.spandigital.ddd.freelancer.domain.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RestCustomerRepository implements CustomerRepository {

    private final RestTemplate restTemplate;

    public RestCustomerRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Customer> findById(CustomerId customerId) {

        final Customer customer = this.restTemplate
                .getForObject("http://localhost:8081/customers/" + customerId, Customer.class);

        return customer == null ? Optional.empty() : Optional.of(customer);
    }
}
