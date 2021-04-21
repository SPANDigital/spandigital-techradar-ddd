package com.spandigital.ddd.freelancer.domain;

import com.spandigital.ddd.freelancer.domain.exception.DomainException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class TimesheetServiceImpl implements TimesheetService {

    private final TimesheetRepository timesheetRepository;

    public TimesheetServiceImpl(TimesheetRepository timesheetRepository) {
        this.timesheetRepository = timesheetRepository;
    }

    @Override
    public void submit(Timesheet timesheet) throws DomainException {

        this.assertTimesheetNotAlreadySubmitted(timesheet);

        this.timesheetRepository.save(timesheet);
    }

    private void assertTimesheetNotAlreadySubmitted(Timesheet timesheet) throws DomainException {

        final TimesheetId timesheetId = timesheet.getId();

        if (this.timesheetRepository.existsById(timesheetId)) {
            throw new DomainException(format("Timesheet with ID [%s] already submitted.", timesheetId));
        }
    }
}
