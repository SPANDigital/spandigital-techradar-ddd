package com.spandigital.bbom.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContactInformationDto extends AbstractDto {

    private ContactTypeDto contactType;

    private String value;
}
