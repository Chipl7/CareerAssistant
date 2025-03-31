package com.careerassistant.repository;


import com.careerassistant.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    Page<Vacancy> findByKeywordIgnoreCase(String keyword, Pageable pageable);
    Page<Vacancy> findAll(Pageable pageable);
    Page<Vacancy> findByNameIgnoreCaseContaining(String keyword, Pageable pageable);
    long countByNameIgnoreCaseContaining(String keyword);
    long count();
}
