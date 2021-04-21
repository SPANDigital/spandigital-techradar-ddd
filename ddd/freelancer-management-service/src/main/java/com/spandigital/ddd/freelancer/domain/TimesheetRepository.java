package com.spandigital.ddd.freelancer.domain;

import java.util.Optional;

public interface TimesheetRepository {

    void deleteById(TimesheetId timesheetId);

    boolean existsById(TimesheetId id);

    Optional<Timesheet> findById(TimesheetId id);

    Timesheet save(Timesheet timesheet);
}
