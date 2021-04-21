package com.spandigital.ddd.freelancer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static com.spandigital.ddd.freelancer.domain.ProjectStatus.ACTIVE;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    @Id
    private ProjectId id;

    private String name;

    private ProjectStatus status;

    private CustomerId customerId;

    public boolean isActive() {
        return this.status == ACTIVE;
    }
}
