package com.spandigital.bbom.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectDto extends AbstractDto {

    private String name;

    private CustomerDto customer;

    private List<FreelancerDto> freelancers;

    private List<TimesheetDto> timesheets;
}
