package br.com.axellbrendow.diospringrestapijaxrs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.axellbrendow.diospringrestapijaxrs.exception.NotFoundException;

@ControllerAdvice
public class ResourceAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public void notFound() {}
}
