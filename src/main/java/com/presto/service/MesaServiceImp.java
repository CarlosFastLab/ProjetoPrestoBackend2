package com.presto.service;

import com.presto.model.Mesa;
import com.presto.model.Pedido;
import com.presto.repository.MesaRepository;
import com.presto.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MesaServiceImp implements MesaService{

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public ResponseEntity<?> removePedido(long idMesa, long idPedido) {
        Mesa mesa = mesaRepository.findById(idMesa).get();
        Pedido pedido = pedidoRepository.findById(idPedido).get();
        if (mesa != null) {
            if (mesa.getPedido() != null) {
                mesa.setPedido(null);
                mesaRepository.save(mesa);
                pedido.setMesa(null);
                pedidoRepository.save(pedido);

                return new ResponseEntity<>("Pedido removido com sucesso", HttpStatus.OK);
            }
            return new ResponseEntity<>("A mesa não possui pedidos", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Mesa não existente", HttpStatus.NOT_FOUND);
    }
}
