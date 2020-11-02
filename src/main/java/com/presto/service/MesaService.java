package com.presto.service;

import org.springframework.http.ResponseEntity;

public interface MesaService {
    ResponseEntity<?> removePedido(long idMesa, long idPedido);
}
