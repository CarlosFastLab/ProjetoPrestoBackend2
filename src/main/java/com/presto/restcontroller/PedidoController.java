package com.presto.restcontroller;

import com.presto.model.Mesa;
import com.presto.model.Pedido;
import com.presto.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class PedidoController {
    PedidoRepository pedidoRepository;
    @PostMapping("/create")
    public ResponseEntity<?> criarMesa(@RequestBody Pedido pedido){
        if(pedido != null){
            return new ResponseEntity<>(pedidoRepository.save(pedido), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/mesas")
    public ResponseEntity<List<Pedido>> getAllMesas(){
        List<Pedido> pedidos = new ArrayList<Pedido>();
        pedidoRepository.findAll().forEach(pedidos::add);
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

//    @PutMapping("/addmesa/{id}")
//    {
//
//    }
}
