package com.careerassistant.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HhVacancyDesc {
    private Long id;
    private String name;
    @JsonProperty("alternate_url")
    private String alternateUrl;
    private Experience experience;
    private Salary salary;
    private Address address;
    private WorkFormat workFormat;
    private ProfessionalRoles professionalRoles;


    @Getter @Setter
    public static class Experience{
        private ExpId id;
        private String name;
    }
    public static enum ExpId{
        noExperience, between1And3, between3And6, moreThan6;
    }

    @Getter @Setter
    public static class Salary {
        private Long from;
    }

    @Getter @Setter
    public static class Address {
        private String city;
        private String street;
    }

    @Getter @Setter
    public static class WorkFormat {
        private FormatId id;
        private String name;
    }
    public static enum FormatId {
        ON_SITE, HYBRID, REMOTE;
    }

    @Getter @Setter
    public static class ProfessionalRoles {
        private String name;
    }
}
