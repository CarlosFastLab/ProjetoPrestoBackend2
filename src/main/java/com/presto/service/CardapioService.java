package com.presto.service;


import org.springframework.http.ResponseEntity;

public interface CardapioService {

    ResponseEntity<?> filtraProdutos(String tipo, String nome);
}
