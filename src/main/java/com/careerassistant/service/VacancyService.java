package com.careerassistant.service;


import com.careerassistant.exception.VacancyNotFoundException;
import com.careerassistant.model.Vacancy;
import com.careerassistant.repository.VacancyRepository;
import com.careerassistant.service.dto.HhArea;
import com.careerassistant.service.dto.HhResponsePage;
import com.careerassistant.service.dto.HhVacancyDesc;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final int PER_PAGE = 100;
    private final RestTemplate restTemplate;
    private VacancyRepository vacancyRepository;

    @Autowired
    public VacancyService(RestTemplateBuilder restTemplateBuilder, VacancyRepository vacancyRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.vacancyRepository = vacancyRepository;
    }


    public void loadVacancy(String vacancyName) {
        ResponseEntity<List<HhArea>> response = restTemplate.exchange("https://api.hh.ru/areas" , HttpMethod.GET, null, new ParameterizedTypeReference<List<HhArea>>() {
        });
        for (HhArea country : response.getBody()) {
            if (!country.getAreas().isEmpty()) {
                for (HhArea reg : country.getAreas()) {
                    loadRegion(vacancyName, reg.getId());
                }
            }
        }
    }


    public void loadRegion(String vacancyName, Long regId) {
        try {
            for (int i = 0;i < 20;i++) {
                List<Vacancy> vacancyList = null;
                try {
                    vacancyList = loadPage(i, PER_PAGE, regId, vacancyName);
                } catch (Exception e){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e1) {
                        Thread.currentThread().interrupt();
                    }
                    try {
                        vacancyList = loadPage(i, PER_PAGE, regId, vacancyName);
                    } catch (Exception e2) {
                    }
                }
                if(vacancyList != null && !vacancyList.isEmpty()) {
                    vacancyRepository.saveAll(vacancyList);
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Vacancy> loadPage(int pageNum, int perPage, long locId, String vacancyName){
        List<Vacancy> result = new ArrayList<>();
        String url = "https://api.hh.ru/vacancies?page=" + pageNum + "&per_page=" + perPage + "&text=" + vacancyName + "&area=" + locId;
        ResponseEntity<HhResponsePage> response = restTemplate.getForEntity(url, HhResponsePage.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            HhResponsePage vacancyListResponse = response.getBody();
            if (vacancyListResponse != null && vacancyListResponse.getItems() != null && !vacancyListResponse.getItems().isEmpty()) {
                for (HhVacancyDesc vacancy : vacancyListResponse.getItems()) {
                    if (vacancy.getId() != null) {
                        System.out.println(vacancy.getId());
                        System.out.println(vacancy.getName());
                        System.out.println(vacancy.getExperience().getName());
                        System.out.println(vacancy.getAlternateUrl());
                        result.add(new Vacancy(vacancy, vacancyName));
                    }
                }
            }
        }
        return result;
    }

    public void save(Vacancy vacancy) {
        vacancyRepository.save(vacancy);
    }

    public Page<Vacancy> findAll(Pageable pageable) {
        return vacancyRepository.findAll(pageable);
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

//    public List<Vacancy> findByKeyword(String keyword) {
//        return vacancyRepository.findByKeyword(keyword);
//    }

    public Page<Vacancy> findByKeyword(String keyword, Pageable pageable) {
        return vacancyRepository.findByKeyword(keyword, pageable);
    }
}