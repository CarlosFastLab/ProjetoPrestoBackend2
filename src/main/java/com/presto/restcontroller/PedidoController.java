package com.presto.restcontroller;

import com.presto.model.Mesa;
import com.presto.model.Pedido;
import com.presto.model.Produto;
import com.presto.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequestMapping("pedido")
@RestController
public class PedidoController {
    @Autowired
    PedidoRepository pedidoRepository;
    @PostMapping("/create")
    public ResponseEntity<?> criarPedido(@RequestBody Pedido pedido){
        try {
            Pedido _pedido = pedidoRepository.save(pedido);
            return new ResponseEntity<>(_pedido, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
//        if(pedido != null){
//            return new ResponseEntity<>(pedidoRepository.save(pedido), HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> getAllMesas(){
        List<Pedido> pedidos = new ArrayList<Pedido>();
        pedidoRepository.findAll().forEach(pedidos::add);
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PutMapping("/addmesa/{id}")
    public ResponseEntity<?> addMesaById(@RequestBody Mesa mesa, @PathVariable("id") long id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        if (pedido.isPresent()){
            pedido.get().setMesa(mesa);
            return new ResponseEntity<>(pedidoRepository.save(pedido.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/addprodutos/{id}")

    public ResponseEntity<?> addProdutos(@RequestBody List<Produto> produtos , @PathVariable("id") long id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        if (pedido.isPresent()){
            for(Produto produto : produtos ){
                pedido.get().setItensDoPedido(produto);
            }
            return new ResponseEntity<>(pedidoRepository.save(pedido.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
