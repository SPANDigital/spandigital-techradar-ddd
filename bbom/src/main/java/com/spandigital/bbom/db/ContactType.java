package com.spandigital.bbom.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class ContactType extends AbstractEntity {

    private String name;

    private boolean email;

    private boolean fax;

    private boolean mobile;
}
