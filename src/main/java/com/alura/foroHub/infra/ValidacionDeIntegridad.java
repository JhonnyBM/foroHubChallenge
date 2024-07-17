package com.alura.foroHub.infra;

public class ValidacionDeIntegridad extends RuntimeException {
    public ValidacionDeIntegridad(String message) {
        super(message);
    }
}

