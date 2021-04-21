package com.spandigital.bbom.controller;

import com.spandigital.bbom.db.*;
import com.spandigital.bbom.dto.TimesheetDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@Transactional
@RestController
public class FreelancerController {

    private final FreelancerRepository freelancerRepository;

    public FreelancerController(FreelancerRepository freelancerRepository) {
        this.freelancerRepository = freelancerRepository;
    }

    @PostMapping("/freelancer/{freelancerId}/timesheets")
    public void postTimesheet(@PathVariable("freelancerId") Long freelancerId,
                              @RequestBody TimesheetDto timesheetDto) {

        final Freelancer freelancer = this.freelancerRepository
                .findById(freelancerId)
                .orElseThrow(() -> handleFreelancerNotFound(freelancerId));

        final Long timesheetProjectId = timesheetDto.getProject().getId();
        final List<Project> freelancerProjects = freelancer.getProjects();

        Project project = null;
        for (Project freelancerProject : freelancerProjects) {
            if (freelancerProject.getId().equals(timesheetProjectId)) {
                project = freelancerProject;
                break;
            }
        }

        if (project == null) {
            handleFreelancerNotAssignedToProject(freelancerId, timesheetProjectId);
        }

        if (!project.getStatus().isActive()) {
            handleNoActiveProject(project.getId());
        }

        final Customer customer = project.getCustomer();
        if (customer != null && !customer.getStatus().isActive()) {
            handleNoActiveCustomer(customer.getId());
        }

        final Timesheet timesheet = new Timesheet();
        timesheet.setFreelancer(freelancer);
        timesheet.setProject(project);
        timesheet.setMonth(timesheetDto.getMonth());
        timesheet.setYear(timesheetDto.getYear());
        timesheet.setHoursWorked(timesheetDto.getHoursWorked());

        final List<Timesheet> timesheets = freelancer.getTimesheets();
        if (timesheets.size() == 0) {
            freelancer.setTimesheets(new ArrayList<>());
        }

        timesheets.add(timesheet);
    }

    private RuntimeException handleFreelancerNotFound(Long freelancerId) {
        return new RuntimeException(format("Freelancer with ID [%d] not found.", freelancerId));
    }

    private void handleFreelancerNotAssignedToProject(Long freelancerId, Long projectId) {
        throw new RuntimeException(format(
                "Freelancer with ID [%d] is not assigned to Project with ID [%d].",
                freelancerId,
                projectId
        ));
    }

    private void handleNoActiveProject(Long projectId) {
        throw new RuntimeException(format(
                "No active project with ID [%d].",
                projectId
        ));
    }

    private void handleNoActiveCustomer(Long customerId) {
        throw new RuntimeException(format(
                "No active customer with ID [%d].",
                customerId
        ));
    }
}
