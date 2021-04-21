package com.spandigital.bbom.dto;

import lombok.Data;

@Data
public abstract class AbstractDto {

    private Long id;

    private Long createdById;
}
