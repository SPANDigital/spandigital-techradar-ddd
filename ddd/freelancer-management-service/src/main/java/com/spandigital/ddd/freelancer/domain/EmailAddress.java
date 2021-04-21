package com.spandigital.ddd.freelancer.domain;

import lombok.Data;

@Data
public class EmailAddress implements CommunicationChannel {

    private ContactType type;

    private String value;
}
