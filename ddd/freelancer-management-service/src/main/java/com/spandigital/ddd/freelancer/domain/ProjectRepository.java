package com.spandigital.ddd.freelancer.domain;

import java.util.Optional;

public interface ProjectRepository {

    Optional<Project> findById(ProjectId projectId);
}
