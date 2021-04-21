package com.spandigital.ddd.freelancer.domain;

import lombok.Data;

@Data
public class Address {

    private String streetName1;
    private String streetName2;
    private String zipCode;
    private String city;
}
