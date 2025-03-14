package com.careerassistant.serializer;

import com.careerassistant.model.Vacancy;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class VacancyListResponse {

    private List<Vacancy> items;
    private Integer total;

    public List<Vacancy> getItems() {
        return items;
    }

    public void setItems(List<Vacancy> items) {
        this.items = items;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
