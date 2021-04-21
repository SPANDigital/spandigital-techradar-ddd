package com.spandigital.ddd.freelancer.infrastructure.inmemory;

import com.spandigital.ddd.freelancer.domain.Timesheet;
import com.spandigital.ddd.freelancer.domain.TimesheetId;
import com.spandigital.ddd.freelancer.domain.TimesheetRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryTimesheetRepository implements TimesheetRepository {

    private final List<Timesheet> timesheets = new ArrayList<>();

    @Override
    public void deleteById(TimesheetId timesheetId) {
        this.findById(timesheetId)
                .ifPresent(this.timesheets::remove);
    }

    @Override
    public boolean existsById(TimesheetId id) {
        return this
                .findById(id)
                .isPresent();
    }

    @Override
    public Optional<Timesheet> findById(TimesheetId id) {
        return this
                .timesheets
                .stream()
                .filter(timesheet -> timesheet.is(id))
                .findFirst();
    }

    @Override
    public Timesheet save(Timesheet timesheet) {
        final TimesheetId timesheetId = timesheet.getId();

        if (this.existsById(timesheetId)) {
            this.deleteById(timesheetId);
        }

        this.timesheets.add(timesheet);

        return timesheet;
    }
}
