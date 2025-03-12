package com.careerassistant.exception;

public class VacancyNotFoundException extends RuntimeException{

    public VacancyNotFoundException(Long id) {
        super("Cloud not find vacancy " + id);
    }
}
