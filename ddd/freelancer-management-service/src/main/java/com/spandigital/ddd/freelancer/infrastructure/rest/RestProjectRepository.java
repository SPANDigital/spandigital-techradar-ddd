package com.spandigital.ddd.freelancer.infrastructure.rest;

import com.spandigital.ddd.freelancer.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RestProjectRepository implements ProjectRepository {

    private final RestTemplate restTemplate;

    public RestProjectRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Project> findById(ProjectId projectId) {

        final Project project = this.restTemplate
                .getForObject("http://localhost:8082/projects/" + projectId, Project.class);

        return project == null ? Optional.empty() : Optional.of(project);
    }
}
