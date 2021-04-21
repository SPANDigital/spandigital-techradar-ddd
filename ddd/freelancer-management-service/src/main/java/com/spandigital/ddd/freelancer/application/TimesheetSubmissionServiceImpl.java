package com.spandigital.ddd.freelancer.application;

import com.spandigital.ddd.freelancer.application.exception.ApplicationException;
import com.spandigital.ddd.freelancer.domain.*;
import com.spandigital.ddd.freelancer.domain.exception.DomainException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class TimesheetSubmissionServiceImpl implements TimesheetSubmissionService {

    private final CustomerRepository customerRepository;
    private final FreelancerRepository freelancerRepository;
    private final ProjectRepository projectRepository;
    private final TimesheetService timesheetService;

    public TimesheetSubmissionServiceImpl(CustomerRepository customerRepository,
                                          FreelancerRepository freelancerRepository,
                                          ProjectRepository projectRepository,
                                          TimesheetService timesheetService) {

        this.customerRepository = customerRepository;
        this.projectRepository = projectRepository;
        this.freelancerRepository = freelancerRepository;
        this.timesheetService = timesheetService;
    }

    @Override
    public void submitTimesheet(Timesheet timesheet) throws ApplicationException, DomainException {

        final FreelancerId freelancerId = timesheet.getFreelancerId();

        final Freelancer freelancer = this.findFreelancer(freelancerId);

        final ProjectId projectId = timesheet.getProjectId();

        this.assertFreelancerAssignedToProject(freelancer, projectId);

        Project project = this.findActiveProject(projectId);

        final CustomerId customerId = project.getCustomerId();

        this.assertCustomerIsActive(customerId);

        this.timesheetService.submit(timesheet);
    }

    private Freelancer findFreelancer(FreelancerId freelancerId) throws ApplicationException {
        return this.freelancerRepository
                .findById(freelancerId)
                .orElseThrow(() -> handleFreelancerNotFound(freelancerId));
    }

    private void assertFreelancerAssignedToProject(Freelancer freelancer, ProjectId projectId)
            throws ApplicationException {

        if (!freelancer.assignedTo(projectId)) {
            handleFreelancerNotAssignedToProject(freelancer.getId(), projectId);
        }
    }

    private Project findActiveProject(ProjectId projectId) throws ApplicationException {
        return this.projectRepository
                .findById(projectId)
                .filter(Project::isActive)
                .orElseThrow(() -> handleNoActiveProject(projectId));
    }

    private void assertCustomerIsActive(CustomerId customerId) throws ApplicationException {
        this.customerRepository
                .findById(customerId)
                .filter(Customer::isActive)
                .orElseThrow(() -> handleNoActiveCustomer(customerId));
    }

    private void handleFreelancerNotAssignedToProject(FreelancerId freelancerId, ProjectId projectId)
            throws ApplicationException {

        throw new ApplicationException(format(
                "Freelancer with ID [%s] is not assigned to Project with ID [%s].",
                freelancerId,
                projectId
        ));
    }

    private ApplicationException handleNoActiveProject(ProjectId projectId) {
        return new ApplicationException(format(
                "No active project with ID [%s].",
                projectId
        ));
    }

    private ApplicationException handleNoActiveCustomer(CustomerId customerId) {
        return new ApplicationException(format(
                "No active customer with ID [%s].",
                customerId
        ));
    }

    private ApplicationException handleFreelancerNotFound(FreelancerId freelancerId) {
        return new ApplicationException(format("Freelancer with ID [%s] not found.", freelancerId));
    }
}
