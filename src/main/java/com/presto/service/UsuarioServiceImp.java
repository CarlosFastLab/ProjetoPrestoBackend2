package com.presto.service;

import com.presto.model.Usuario;
import com.presto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImp implements  UsuarioService{
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public ResponseEntity<?> validaEmail(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmailContaining(email);
        if(usuario.isPresent()){
            return new ResponseEntity<>("Usuario existente!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }



}
