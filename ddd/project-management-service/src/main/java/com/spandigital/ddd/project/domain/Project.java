package com.spandigital.ddd.project.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Project {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id"))
    private ProjectId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "created_by"))
    private UserId createdBy;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "customer_id"))
    private CustomerId customerId;
}
