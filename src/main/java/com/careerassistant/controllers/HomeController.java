package com.careerassistant.controllers;

import com.careerassistant.model.Vacancy;
import com.careerassistant.service.VacancyService;
import org.apache.tomcat.Jar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    private VacancyService vacancyService;

    @Autowired
    public HomeController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping("/")
    public String home(@RequestParam(value = "vacancyName", required = false) String vacancyName, Model model) {
        model.addAttribute("vacancyName", new String());
        model.addAttribute("vacancyList", vacancyService.findAll());
        return "home";
    }

    @PostMapping("/")
    public String vacancy(@RequestParam("vacancyName") String vacancyName, Model model) {
        vacancyService.findAndSaveVacancy(vacancyName);
        return "redirect:/";
    }
}
