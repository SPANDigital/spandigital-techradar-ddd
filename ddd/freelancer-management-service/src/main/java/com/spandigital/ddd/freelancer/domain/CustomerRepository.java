package com.spandigital.ddd.freelancer.domain;

import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> findById(CustomerId customerId);
}
