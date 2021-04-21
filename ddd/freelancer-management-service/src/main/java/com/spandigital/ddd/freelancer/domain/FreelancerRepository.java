package com.spandigital.ddd.freelancer.domain;

import java.util.Optional;

public interface FreelancerRepository {

    boolean existsByEmailAddresses(Iterable<EmailAddress> emailAddresses);

    Iterable<Freelancer> findByEmailAddresses(Iterable<EmailAddress> emailAddresses);

    Freelancer save(Freelancer freelancer);

    boolean existsById(FreelancerId freelancerId);

    Optional<Freelancer> findById(FreelancerId freelancerId);

    void deleteById(FreelancerId freelancerId);
}
