package com.spandigital.ddd.freelancer.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class ProjectId implements Serializable {

    @JsonValue
    private String value;

    public ProjectId() {
        this.value = UUID.randomUUID().toString();
    }

    public ProjectId(String value) {
        this.value = value;
    }

    public static ProjectId fromString(String value) {
        return new ProjectId(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
