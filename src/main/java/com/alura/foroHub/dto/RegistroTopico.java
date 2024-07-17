package com.alura.foroHub.dto;

import jakarta.validation.constraints.NotBlank;

public record RegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String autor,
        @NotBlank
        String curso) {
}
