package com.spandigital.ddd.freelancer.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class FreelancerId implements Serializable {

    @JsonValue
    private String value;

    public FreelancerId() {
        this.value = UUID.randomUUID().toString();
    }

    private FreelancerId(String value) {
        this.value = value;
    }

    public static FreelancerId fromString(String value) {
        return new FreelancerId(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
