package com.presto.service;


import org.springframework.http.ResponseEntity;

public interface UsuarioService {

    ResponseEntity<?> validaEmail(String email);
}
