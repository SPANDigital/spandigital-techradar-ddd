package com.spandigital.bbom.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContactTypeDto extends AbstractDto {

    private String name;

    private boolean isEmail;

    private boolean isPhone;

    private boolean isMobile;
}
