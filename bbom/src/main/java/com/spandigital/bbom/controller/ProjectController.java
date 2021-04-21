package com.spandigital.bbom.controller;

import com.spandigital.bbom.db.*;
import com.spandigital.bbom.db.exceptions.EntityNotFoundException;
import com.spandigital.bbom.dto.TimesheetDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Transactional
@RestController
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @PostMapping("/project/{projectId}/timesheets")
    public void postTimesheet(@PathVariable("projectId") Long projectId,
                              @RequestBody TimesheetDto timesheetDto) throws EntityNotFoundException {

        final Optional<Project> optionalProject = this.projectRepository.findById(projectId);

        if (optionalProject.isEmpty()) {
            throw new EntityNotFoundException();
        }

        Project project = optionalProject.get();

        if (!project.getStatus().isActive()) {
            throw new RuntimeException(format(
                    "Project with ID [%s] is not in an active state",
                    project.getId()
            ));
        }

        final Long timesheetFreelancerId = timesheetDto.getFreelancer().getId();
        final List<Freelancer> projectFreelancers = project.getFreelancers();

        Freelancer freelancer = null;
        for (Freelancer projectFreelancer : projectFreelancers) {
            if (projectFreelancer.getId().equals(timesheetFreelancerId)) {
                freelancer = projectFreelancer;
                break;
            }
        }

        if (freelancer == null) {
            throw new RuntimeException(format(
                    "Freelancer with ID [%s] is not assigned to Project with ID [%s]",
                    timesheetFreelancerId,
                    projectId
            ));
        }

        final Customer customer = project.getCustomer();
        if (!customer.getStatus().isActive()) {
            throw new RuntimeException(format(
                    "Customer with ID [%s] is not in an active state",
                    customer.getId()
            ));
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
}
