package com.alura.foroHub.repository;

import com.alura.foroHub.entity.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TopicosRepository extends JpaRepository<Topico, Integer> {

    boolean existsByTituloOrMensaje(String title, String message);
    Page<Topico> findAllByOrderByFechaCreacionAsc(Pageable pageable);
    List<Topico> findTop10ByOrderByFechaCreacionAsc();
    List<Topico> findByCursoAndFechaCreacionBetween(String curso,LocalDateTime startOfYear, LocalDateTime endOfYear);
}
