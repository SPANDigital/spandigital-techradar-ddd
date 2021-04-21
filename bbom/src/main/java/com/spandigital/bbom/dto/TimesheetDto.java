package com.spandigital.bbom.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TimesheetDto extends AbstractDto {

    private FreelancerDto freelancer;

    private ProjectDto project;

    private int year;

    private int month;

    private int hoursWorked;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class FreelancerDto extends AbstractDto {

        private String streetName1;

        private String streetName2;

        private String zipCode;

        private String city;

        private ContactInformationDto contactInformation;

        private List<ProjectDto> projects;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class ProjectDto extends AbstractDto {

        private String name;

        private CustomerDto customer;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class CustomerDto extends AbstractDto {

        private String name;
    }
}
