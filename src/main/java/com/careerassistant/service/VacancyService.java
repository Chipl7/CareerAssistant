package com.careerassistant.service;


import com.careerassistant.exception.VacancyNotFoundException;
import com.careerassistant.model.Vacancy;
import com.careerassistant.repository.VacancyRepository;
import com.careerassistant.serializer.VacancyListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final RestTemplate restTemplate;
    private VacancyRepository vacancyRepository;

    @Autowired
    public VacancyService(RestTemplateBuilder restTemplateBuilder, VacancyRepository vacancyRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.vacancyRepository = vacancyRepository;
    }


    //TODO
    public void findAndSaveVacancy() {
        try {
            for (int i = 0;i < 100;i++) {
                ResponseEntity<VacancyListResponse> response = restTemplate.getForEntity("https://api.hh.ru/vacancies?page=" + i + "&text=Java", VacancyListResponse.class);
                if (response.getStatusCode() == HttpStatus.OK) {
                    VacancyListResponse vacancyListResponse = response.getBody();
                    if (vacancyListResponse != null && vacancyListResponse.getItems() != null && !vacancyListResponse.getItems().isEmpty()) {
                        for (Vacancy vacancy : vacancyListResponse.getItems()) {
                            if (vacancy.getId() != null) {
                                Vacancy vacancyNew = new Vacancy();
                                vacancyNew.setName(vacancy.getName());
                                vacancyNew.setAlternateUrl(vacancy.getAlternateUrl());
                                vacancyRepository.save(vacancyNew);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(Vacancy vacancy) {
        vacancyRepository.save(vacancy);
    }

    public List<Vacancy> findAll() {
        return vacancyRepository.findAll();
    }

    public Vacancy findById(Long id) {
        return vacancyRepository.findById(id).orElseThrow(() -> new VacancyNotFoundException(id));
    }

    public Optional<Vacancy> update(Vacancy newVacancy, Long id) {
        return vacancyRepository.
                findById(id).
                map(vacancy -> {
                    vacancy.setName(newVacancy.getName());
                    return vacancyRepository.save(vacancy);
                });
    }

    public void delete(Long id) {
        vacancyRepository.deleteById(id);
    }
}
