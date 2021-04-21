package com.spandigital.ddd.customer.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Customer {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id"))
    private CustomerId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "created_by"))
    private UserId createdBy;

    private String name;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;
}
