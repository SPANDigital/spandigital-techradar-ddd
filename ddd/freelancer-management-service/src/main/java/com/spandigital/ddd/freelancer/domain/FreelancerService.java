package com.spandigital.ddd.freelancer.domain;

import com.spandigital.ddd.freelancer.domain.exception.DomainException;

public interface FreelancerService {

    void register(Freelancer freelancer) throws DomainException;
}
