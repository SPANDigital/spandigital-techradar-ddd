package com.spandigital.ddd.project.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "projects")
public interface ProjectRepository extends CrudRepository<Project, ProjectId> {
}
