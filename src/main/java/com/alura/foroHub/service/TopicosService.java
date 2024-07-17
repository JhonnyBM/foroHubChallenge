package com.alura.foroHub.service;

import com.alura.foroHub.dto.RegistroTopico;
import com.alura.foroHub.entity.Topico;

import java.util.List;
import java.util.Optional;

public interface TopicosService {

    List<Topico> findAll();

    Topico save(RegistroTopico registroTopicos);

    Optional<Topico> findById(Integer id);

    List<Topico> getTop10Topicos();

    List<Topico> findByCursoAnio(String curso, String anio);

    Topico update(Topico topicos);

    Topico delete(Topico existingTopicos);
}
