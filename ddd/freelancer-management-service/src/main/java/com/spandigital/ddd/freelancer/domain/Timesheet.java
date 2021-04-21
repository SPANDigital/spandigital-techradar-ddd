package com.spandigital.ddd.freelancer.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Timesheet {

    @Id
    private TimesheetId id;
    private UserId createdBy;
    private FreelancerId freelancerId;
    private ProjectId projectId;
    private Duration timeWorked;

    public Timesheet() {
        this.id = new TimesheetId();
    }

    public boolean is(TimesheetId thatId) {
        return this.id == thatId;
    }
}
