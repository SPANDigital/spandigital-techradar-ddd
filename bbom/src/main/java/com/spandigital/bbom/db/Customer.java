package com.spandigital.bbom.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Customer extends AbstractEntity {

    private String name;

    @ManyToOne
    private CustomerStatus status;

    @OneToMany(mappedBy = "customer")
    private List<Project> projects;
}
