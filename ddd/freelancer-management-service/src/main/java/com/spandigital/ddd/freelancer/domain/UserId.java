package com.spandigital.ddd.freelancer.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class UserId implements Serializable {

    @JsonValue
    private final String value;

    public UserId() {
        this.value = UUID.randomUUID().toString();
    }

    public UserId(String value) {
        this.value = value;
    }

    public static UserId fromString(String value) {
        return new UserId(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
