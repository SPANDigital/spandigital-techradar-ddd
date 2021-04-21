package com.spandigital.bbom.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Project extends AbstractEntity {

    private String name;

    @ManyToOne
    private ProjectStatus status;

    @ManyToOne
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "project_freelancers",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "freelancer_id")}
    )
    private List<Freelancer> freelancers;

    @OneToMany(mappedBy = "project")
    private List<Timesheet> timesheets;
}
