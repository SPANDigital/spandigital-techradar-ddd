package com.spandigital.bbom.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class CustomerStatus extends AbstractEntity {

    private String name;

    private boolean active;

    private boolean inactive;
}
