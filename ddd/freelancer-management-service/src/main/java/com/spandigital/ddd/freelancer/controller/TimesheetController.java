package com.spandigital.ddd.freelancer.controller;

import com.spandigital.ddd.freelancer.application.TimesheetSubmissionService;
import com.spandigital.ddd.freelancer.application.exception.ApplicationException;
import com.spandigital.ddd.freelancer.domain.Timesheet;
import com.spandigital.ddd.freelancer.domain.exception.DomainException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timesheets")
public class TimesheetController {

    private final TimesheetSubmissionService timesheetSubmissionService;

    public TimesheetController(TimesheetSubmissionService timesheetSubmissionService) {
        this.timesheetSubmissionService = timesheetSubmissionService;
    }

    @PostMapping
    public void submitTimesheet(@RequestBody Timesheet timesheet)
            throws DomainException, ApplicationException {

        this.timesheetSubmissionService.submitTimesheet(timesheet);
    }
}
