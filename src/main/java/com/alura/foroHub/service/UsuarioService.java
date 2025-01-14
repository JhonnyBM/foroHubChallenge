package com.alura.foroHub.service;

import com.alura.foroHub.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> findAll();

    Optional<Usuario> findById(Integer id);
}
