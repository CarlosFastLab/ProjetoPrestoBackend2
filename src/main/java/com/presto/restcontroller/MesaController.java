package com.presto.restcontroller;

import com.presto.model.Mesa;
import com.presto.model.Pedido;
import com.presto.repository.MesaRepository;
import com.presto.repository.PedidoRepository;
import com.presto.repository.ProdutoRepository;
import com.presto.service.MesaService;
import com.presto.service.MesaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RequestMapping("mesa")
@RestController
public class MesaController {
    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private MesaService mesaService;

    @PostMapping("/create")
    public ResponseEntity<?> criarMesa(@RequestBody Mesa mesa){
        if(mesa != null){
            return new ResponseEntity<>(mesaRepository.save(mesa), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/mesas")
    public ResponseEntity<List<Mesa>> getAllMesas(){
        List<Mesa> mesas = new ArrayList<Mesa>();
        mesaRepository.findAll().forEach(mesas::add);
        return new ResponseEntity<>(mesas, HttpStatus.OK);
    }

    @PutMapping("/addpedido/{nome}")
    public ResponseEntity<?> addPedido(@PathVariable("nome") String nome, @RequestBody Pedido pedido){
        Optional<Mesa> mesa = mesaRepository.findByNomeContaining(nome);

        if (mesa.isPresent()){
            pedido.setMesa(mesa.get());
            Pedido pedido1 = pedidoRepository.save(pedido);

            mesa.get().setPedido(pedido);
            mesaRepository.save(mesa.get());
            return new ResponseEntity<>(pedido1,HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/removepedido/{idMesa}/{idPedido}")
    public ResponseEntity<?> removePedido(@PathVariable("idMesa") long idMesa, @PathVariable("idPedido") long idPedido) {
        ResponseEntity retorno = mesaService.removePedido(idMesa, idPedido);
        return new ResponseEntity<>(retorno.getBody(), retorno.getStatusCode());
    }

    @GetMapping("/getpedidomesa/{id}")
    public ResponseEntity<?> getPedidoDaMesa(@PathVariable("id") long id){
        Mesa mesa = mesaRepository.findById(id).get();
        if (mesa != null) {
            Pedido pedido = mesa.getPedido();
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("starttimer/{id}")
    public ResponseEntity<?> start(@PathVariable("id") long id){


        return null;
    }
}
