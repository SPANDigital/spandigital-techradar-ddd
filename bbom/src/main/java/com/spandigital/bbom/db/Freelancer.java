package com.spandigital.bbom.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Freelancer extends AbstractEntity {

    private String streetName1;

    private String streetName2;

    private String zipCode;

    private String city;

    @OneToOne
    private ContactInformation contactInformation;

    @ManyToMany
    @JoinTable(
            name = "project_freelancers",
            joinColumns = {@JoinColumn(name = "freelancer_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private List<Project> projects;

    @OneToMany(mappedBy = "freelancer")
    private List<Timesheet> timesheets;
}
