package com.alura.foroHub.service;

import com.alura.foroHub.entity.Usuario;
import com.alura.foroHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Usuario> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        return userRepository.findById(id);
    }
}
