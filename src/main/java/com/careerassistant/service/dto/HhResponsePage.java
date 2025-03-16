package com.careerassistant.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class HhResponsePage {
    private List<HhVacancyDesc> items = new ArrayList<>();
}
