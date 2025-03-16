package com.careerassistant.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class HhArea {
    private Long id;
    private Long parent_id;
    private String name;
    private List<HhArea> areas = new ArrayList<>();
}
