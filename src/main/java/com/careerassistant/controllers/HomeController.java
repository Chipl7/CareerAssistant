package com.careerassistant.controllers;

import com.careerassistant.model.Vacancy;
import com.careerassistant.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private VacancyService vacancyService;

    @Autowired
    public HomeController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping("/")
    public String home(Model model) {
        vacancyService.findAndSaveVacancy();
//        model.addAttribute("vacancyList", vacancyService.findAll());
//        model.addAttribute("vacancy", new Vacancy());
        return "home";
    }
}
