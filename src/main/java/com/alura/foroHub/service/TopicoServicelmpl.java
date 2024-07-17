package com.alura.foroHub.service;

import com.alura.foroHub.dto.RegistroTopico;
import com.alura.foroHub.entity.Topico;
import com.alura.foroHub.infra.DuplicateTopicException;
import com.alura.foroHub.infra.ValidacionDeIntegridad;
import com.alura.foroHub.repository.TopicosRepository;
import com.alura.foroHub.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoServicelmpl implements TopicosService{

    @Autowired
    private TopicosRepository topicosRepository;
    @Override
    public List<Topico> findAll() {
        return topicosRepository.findAll();
    }

    @Override
    public Topico save(RegistroTopico registroTopicos) {
        if (topicosRepository.existsByTituloOrMensaje(registroTopicos.titulo(),
                registroTopicos.mensaje())) {
            throw new DuplicateTopicException("Ya existe topico con el mismo título o mensaje.");
        }
        Topico topicos = new Topico();
        topicos.setTitulo(registroTopicos.titulo());
        topicos.setMensaje(registroTopicos.mensaje());
        topicos.setAutor(registroTopicos.autor());
        topicos.setCurso(registroTopicos.curso());
        topicos.setFechaCreacion(LocalDateTime.now());
        topicos.setEstado(Constantes.ESTADO_ACTIVO);
        return topicosRepository.save(topicos);
    }

    @Override
    public Optional<Topico> findById(Integer id) {
        return topicosRepository.findById(id);
    }

    public List<Topico> getTop10Topicos() {
        return topicosRepository.findTop10ByOrderByFechaCreacionAsc();
    }

    @Override
    public List<Topico> findByCursoAnio(String curso, String anio) {
        int yearInt = Integer.parseInt(anio);
        LocalDateTime startOfYear = LocalDateTime.of(yearInt, 1, 1, 0, 0);
        LocalDateTime endOfYear = LocalDateTime.of(yearInt, 12, 31, 23, 59, 59);
        return topicosRepository.findByCursoAndFechaCreacionBetween(curso,startOfYear, endOfYear);
    }

    @Override
    public Topico update(Topico topicos) {
        if (topicos.getTitulo().isEmpty()){
            throw new ValidacionDeIntegridad("El campo Titulo no debe estar vacio");
        }
        if (topicos.getMensaje().isEmpty()){
            throw new ValidacionDeIntegridad("El campo Mensaje no debe estar vacio");
        }
        if (topicos.getAutor().isEmpty()){
            throw new ValidacionDeIntegridad("El campo Autror no debe estar vacio");
        }
        if (topicos.getCurso().isEmpty()){
            throw new ValidacionDeIntegridad("El campo Curso no debe estar vacio");
        }

        return topicosRepository.save(topicos);
    }

    @Override
    public Topico delete(Topico existingTopicos) {
        existingTopicos.setEstado(Constantes.ESTADO_INACTIVO);
        return existingTopicos;
    }
}
