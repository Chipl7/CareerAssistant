package com.careerassistant.controllers;


import com.careerassistant.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class HomeController {
    private final VacancyService vacancyService;

    @Autowired
    public HomeController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping("/")
    public String home(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        model.addAttribute("vacancyList", vacancyService.findByKeyword(keyword));
        return "home";
    }

    @PostMapping("/")
    public String vacancy(@RequestParam("vacancyName") String vacancyName, Model model) {
        return "redirect:/";
    }

    @GetMapping("/{vacancyName}")
    public String refreshVacancy(@PathVariable String vacancyName) {
        vacancyService.loadVacancy(vacancyName);
        return "home";
    }
}
