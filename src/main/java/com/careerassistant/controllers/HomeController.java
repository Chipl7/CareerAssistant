package com.careerassistant.controllers;


import com.careerassistant.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HomeController {
    private final VacancyService vacancyService;

    @Autowired
    public HomeController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping("/")
    public String home(@RequestParam(value = "keyword", required = false) String keyword,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "20") int limit, Model model) {
        model.addAttribute("totalVacancy", vacancyService.count());
        model.addAttribute("keyword", keyword != null ? keyword : "");
        model.addAttribute("vacancyList", vacancyService.findAll(PageRequest.of(page, limit)));
        return "home";
    }

    @GetMapping("/page={id}/{keyword}")
    public String vacancyPage(@PathVariable String keyword,
                              @RequestParam(defaultValue = "20") int limit,
                              @PathVariable int id, Model model) {
        model.addAttribute("totalVacancy", vacancyService.countByNameIgnoreCaseContaining(keyword.replace("+", " ")));
        model.addAttribute("keyword", keyword != null ? keyword : "");
        model.addAttribute("vacancyList", vacancyService.findByKeywordIgnoreCase(keyword.replace("+", " "), PageRequest.of(id, limit)));
        return "home";
    }

    @PostMapping("/")
    public String vacancy(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(defaultValue = "20") int limit, Model model) {
        model.addAttribute("totalVacancy", vacancyService.countByNameIgnoreCaseContaining(keyword.replace("+", " ")));
        model.addAttribute("keyword", keyword != null ? keyword : "");
        model.addAttribute("vacancyList", vacancyService.findByNameIgnoreCaseContaining(keyword, PageRequest.of(0, limit)));
        return "home";
    }

    @GetMapping("/decrease/page={id}/{keyword}")
    public String decreaseVacancy(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(defaultValue = "20") int limit,
                          @RequestParam("id") int id, Model model) {
        int newId = id - 1;
        model.addAttribute("totalVacancy", vacancyService.countByNameIgnoreCaseContaining(keyword));
        model.addAttribute("keyword", keyword != null ? keyword : "");
        model.addAttribute("vacancyList", vacancyService.findByNameIgnoreCaseContaining(keyword, PageRequest.of(newId, limit)));
        return "home";
    }

    @GetMapping("/increased/page/{id}/{keyword}")
    public String increasedVacancy(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(defaultValue = "20") int limit,
                          @RequestParam("id") int id, Model model) {
        model.addAttribute("totalVacancy", vacancyService.countByNameIgnoreCaseContaining(keyword));
        model.addAttribute("keyword", keyword != null ? keyword : "");
        model.addAttribute("vacancyList", vacancyService.findByNameIgnoreCaseContaining(keyword, PageRequest.of(id + 1, limit)));
        return "home";
    }

    @GetMapping("/{keyword}")
    public String refreshVacancy(@PathVariable String keyword) {
        vacancyService.loadVacancy(keyword);
        return "redirect:/";
    }
}
