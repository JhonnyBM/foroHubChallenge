package com.alura.foroHub.controller;


import com.alura.foroHub.dto.ActualizarTopico;
import com.alura.foroHub.dto.RegistroTopico;
import com.alura.foroHub.entity.Topico;

import com.alura.foroHub.infra.ValidacionDeIntegridad;
import com.alura.foroHub.service.TopicosService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicosService topicosService;

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene los topicos por ID")
    public ResponseEntity<Optional<Topico>> findById(@PathVariable Integer id) {
        Optional<Topico> topicos  = topicosService.findById(id);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping
    @Operation(summary = "Muestra los primeros 10 topicos ordenados por fecha de creacion.")
    public ResponseEntity<List<Topico>> getTop10Topicos() {
        List<Topico> topicos = topicosService.getTop10Topicos();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/busqueda")
    @Operation(summary = "Muestra resultados filtrando por curso y año.")
    public ResponseEntity<List<Topico>> findByCursoAnio(@RequestParam String curso,
                                                         @Pattern(regexp = "\\d{4}", message = "El año debe ser un número de 4 dígitos")@RequestParam String anio) {
        List<Topico> topicos = topicosService.findByCursoAnio(curso, anio);
        return ResponseEntity.ok(topicos);
    }

    @PostMapping
    @Operation(summary = "Registra un nuevo topico en la base de datos")
    public ResponseEntity<Topico> save(@RequestBody @Valid RegistroTopico registro,
                                        UriComponentsBuilder uriComponentsBuilder) {
        Topico topics = topicosService.save(registro);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topics.getId()).toUri();
        return ResponseEntity.created(url).body(topics);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza los datos de un topico existente")
    public ResponseEntity actualizarTopico(@PathVariable Integer id, @RequestBody ActualizarTopico update) throws ValidacionDeIntegridad {
        Optional<Topico> optionalTopicos = topicosService.findById(id);
        if (optionalTopicos.isEmpty()) {
            throw new ValidacionDeIntegridad("No existe topico con ese id.");
        }
        Topico existingTopicos = optionalTopicos.get();

        existingTopicos.setTitulo(update.titulo());
        existingTopicos.setMensaje(update.mensaje());
        existingTopicos.setAutor(update.autor());
        existingTopicos.setCurso(update.curso());

        Topico updatedTopicos = topicosService.update(existingTopicos);
        return ResponseEntity.ok(updatedTopicos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina un topico registrado - inactivo")
    public ResponseEntity eliminarTopico(@PathVariable Integer id) throws ValidacionDeIntegridad {
        Optional<Topico> optionalTopicos = topicosService.findById(id);
        if (optionalTopicos.isEmpty()) {
            throw new ValidacionDeIntegridad("No existe topico con ese id.");
        }
        Topico existingTopicos = optionalTopicos.get();
        Topico deletedTopicos = topicosService.delete(existingTopicos);
        return ResponseEntity.noContent().build();
    }

}
