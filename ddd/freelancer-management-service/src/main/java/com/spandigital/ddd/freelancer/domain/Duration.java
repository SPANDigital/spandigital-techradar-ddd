package com.spandigital.ddd.freelancer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Duration {

    private Double value;

    private TimeUnit unit;

    public static Duration of(Double value, TimeUnit unit) {
        return new Duration(value, unit);
    }
}
