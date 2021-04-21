package com.spandigital.ddd.freelancer.application;

import com.spandigital.ddd.freelancer.application.exception.ApplicationException;
import com.spandigital.ddd.freelancer.domain.Timesheet;
import com.spandigital.ddd.freelancer.domain.exception.DomainException;

public interface TimesheetSubmissionService {

    void submitTimesheet(Timesheet timesheet) throws ApplicationException, DomainException;

}
