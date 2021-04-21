package com.spandigital.ddd.freelancer.domain;

import com.spandigital.ddd.freelancer.domain.exception.DomainException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreelancerServiceImpl implements FreelancerService {

    private final FreelancerRepository freelancerRepository;

    public FreelancerServiceImpl(FreelancerRepository freelancerRepository) {
        this.freelancerRepository = freelancerRepository;
    }

    @Override
    public void register(Freelancer freelancer) throws DomainException {

        if (freelancer.hasEmailAddresses()) {

            List<EmailAddress> emailAddresses = freelancer.getEmailAddresses();

            if (this.freelancerRepository.existsByEmailAddresses(emailAddresses)) {
                throw new DomainException("Freelancer with email addresses already exists!");
            }
        }

        this.freelancerRepository.save(freelancer);
    }
}
