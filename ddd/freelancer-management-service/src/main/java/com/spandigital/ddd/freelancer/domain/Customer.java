package com.spandigital.ddd.freelancer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static com.spandigital.ddd.freelancer.domain.CustomerStatus.ACTIVE;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

    @Id
    private CustomerId id;
    private String name;
    private CustomerStatus status;

    public boolean isActive() {
        return this.status == ACTIVE;
    }
}
