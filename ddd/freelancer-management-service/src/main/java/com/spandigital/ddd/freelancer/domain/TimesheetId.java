package com.spandigital.ddd.freelancer.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class TimesheetId implements Serializable {

    @JsonValue
    private String value;

    public TimesheetId() {
        this.value = UUID.randomUUID().toString();
    }

    private TimesheetId(String value) {
        this.value = value;
    }

    public static TimesheetId fromString(String value) {
        return new TimesheetId(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
