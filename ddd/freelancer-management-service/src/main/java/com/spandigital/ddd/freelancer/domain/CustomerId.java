package com.spandigital.ddd.freelancer.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.util.UUID;

@Data
public class CustomerId {

    @JsonValue
    private String value;
}
