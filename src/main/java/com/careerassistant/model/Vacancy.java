package com.careerassistant.model;

import com.careerassistant.service.dto.HhVacancyDesc;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "vacancy")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy {

    @Id
    private Long id;

    private String name;

    @Column(name = "url")
    private String alternateUrl;

//    private String experience;
//
////    private Long salary;
//
//    @Column(name = "city_name")
//    private String cityName;
//
//    private String street;
//
//    @Column(name = "work_format")
//    private String workFormat;
//
//    @Column(name = "professional_role")
//    private String professionalRole;

    private String keyword;

    public Vacancy(HhVacancyDesc desc, String keyword) {
        this.id = desc.getId();
        this.name = desc.getName();
        this.alternateUrl = desc.getAlternateUrl();
//        this.experience = desc.getExperience().getName();
//        this.salary = (desc.getSalary().getFrom() != null) ? desc.getSalary().getFrom() : null;
//        this.cityName = desc.getAddress().getCity();
//        this.street = desc.getAddress().getStreet();
//        this.workFormat = desc.getWorkFormat().getName();
//        this.professionalRole = desc.getProfessionalRoles().getName();
        this.keyword = keyword;
    }
}
