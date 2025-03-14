package com.careerassistant.repository;


import com.careerassistant.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findByNameContaining(String vacancyName);
}
