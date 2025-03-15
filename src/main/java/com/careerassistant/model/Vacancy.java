package com.careerassistant.model;

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
    @SequenceGenerator(name = "vacancy_id_seq", sequenceName = "vacancy_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vacancy_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    @JsonProperty("alternate_url")
    private String alternateUrl;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setAlternateUrl(String alternateUrl) {
        this.alternateUrl = alternateUrl;
    }

    public String getAlternateUrl() {
        return alternateUrl;
    }
}
