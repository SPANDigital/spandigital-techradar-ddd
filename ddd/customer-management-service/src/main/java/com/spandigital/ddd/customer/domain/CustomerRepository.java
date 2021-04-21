package com.spandigital.ddd.customer.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "customers")
public interface CustomerRepository extends CrudRepository<Customer, CustomerId> {
}
