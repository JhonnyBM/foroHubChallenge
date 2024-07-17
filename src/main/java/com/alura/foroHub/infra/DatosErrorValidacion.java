package com.alura.foroHub.infra;

import org.springframework.validation.FieldError;

public record DatosErrorValidacion(String campo, String mensaje) {

    public DatosErrorValidacion(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
