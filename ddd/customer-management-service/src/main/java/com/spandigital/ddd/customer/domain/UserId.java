package com.spandigital.ddd.customer.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
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
