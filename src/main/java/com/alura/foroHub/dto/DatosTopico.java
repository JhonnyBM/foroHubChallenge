package com.alura.foroHub.dto;

import com.alura.foroHub.entity.Topico;

import java.time.LocalDateTime;

public record DatosTopico(
        Integer id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String estado,
        String autor,
        String curso) {

    public DatosTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(), topico.getEstado(),
                topico.getAutor(), topico.getCurso());
    }
}
