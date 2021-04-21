package com.spandigital.ddd.project.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
public class CustomerId implements Serializable {

    @JsonValue
    private String value;

    public CustomerId() {
        this.value = UUID.randomUUID().toString();
    }

    public CustomerId(String value) {
        this.value = value;
    }

    public static CustomerId fromString(String value) {
        return new CustomerId(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
