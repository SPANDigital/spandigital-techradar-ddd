package com.spandigital.bbom.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class FreelancerDto extends AbstractDto {

    private String streetName1;

    private String streetName2;

    private String zipCode;

    private String city;

    private ContactInformationDto contactInformation;

    private List<ProjectDto> projects;

    private List<TimesheetDto> timesheets;
}
