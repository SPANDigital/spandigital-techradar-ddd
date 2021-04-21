package com.spandigital.ddd.freelancer.controller;

import com.spandigital.ddd.freelancer.domain.Freelancer;
import com.spandigital.ddd.freelancer.domain.FreelancerId;
import com.spandigital.ddd.freelancer.domain.FreelancerService;
import com.spandigital.ddd.freelancer.domain.exception.DomainException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/freelancer")
public class FreelancerController {

    private final FreelancerService freelancerService;

    public FreelancerController(FreelancerService freelancerService) {
        this.freelancerService = freelancerService;
    }

    @PostMapping
    public void register(@RequestBody Freelancer freelancer) throws DomainException {
        this.freelancerService.register(freelancer);
    }
}
