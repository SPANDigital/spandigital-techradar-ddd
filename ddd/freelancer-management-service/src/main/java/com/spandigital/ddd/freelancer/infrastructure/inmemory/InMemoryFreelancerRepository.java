package com.spandigital.ddd.freelancer.infrastructure.inmemory;

import com.spandigital.ddd.freelancer.domain.EmailAddress;
import com.spandigital.ddd.freelancer.domain.Freelancer;
import com.spandigital.ddd.freelancer.domain.FreelancerId;
import com.spandigital.ddd.freelancer.domain.FreelancerRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryFreelancerRepository implements FreelancerRepository {

    private final List<Freelancer> freelancers = new ArrayList<>();

    @Override
    public boolean existsByEmailAddresses(Iterable<EmailAddress> emailAddresses) {
        return this
                .findByEmailAddresses(emailAddresses)
                .iterator()
                .hasNext();
    }

    @Override
    public Iterable<Freelancer> findByEmailAddresses(Iterable<EmailAddress> emailAddresses) {
        return this
                .freelancers
                .stream()
                .filter(freelancer -> freelancer.hasAny(emailAddresses))
                .collect(Collectors.toList());
    }

    @Override
    public Freelancer save(Freelancer freelancer) {

        final FreelancerId freelancerId = freelancer.getId();

        if (this.existsById(freelancerId)) {
            this.deleteById(freelancer.getId());
        }

        this.freelancers.add(freelancer);

        return freelancer;
    }

    @Override
    public boolean existsById(FreelancerId freelancerId) {
        return this
                .findById(freelancerId)
                .isPresent();
    }

    @Override
    public Optional<Freelancer> findById(FreelancerId freelancerId) {
        return this
                .freelancers
                .stream()
                .filter(freelancer -> freelancer.is(freelancerId))
                .map(Freelancer::of)
                .findFirst();
    }

    @Override
    public void deleteById(FreelancerId freelancerId) {
        this.findById(freelancerId)
                .ifPresent(this.freelancers::remove);
    }
}
