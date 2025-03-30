package com.careerassistant.repository;


import com.careerassistant.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    Page<Vacancy> findByKeyword(String keyword, Pageable pageable);
    Page<Vacancy> findAll(Pageable pageable);
    Page<Vacancy> findAllByNameLike(String keyword);
}
