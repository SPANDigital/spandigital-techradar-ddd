package com.spandigital.bbom.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Timesheet extends AbstractEntity {

    @ManyToOne
    private Freelancer freelancer;

    @ManyToOne
    private Project project;

    private int year;

    private int month;

    private int hoursWorked;
}
