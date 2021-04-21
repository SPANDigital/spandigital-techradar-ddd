package com.spandigital.ddd.freelancer.domain;

import com.spandigital.ddd.freelancer.domain.exception.DomainException;

public interface TimesheetService {
    void submit(Timesheet timesheet) throws DomainException;
}
