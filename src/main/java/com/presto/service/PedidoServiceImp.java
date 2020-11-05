package com.presto.service;

import com.presto.model.Produto;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PedidoServiceImp implements PedidoService {


    @Override
    public Double somarTodal(List<Produto> produtos) {
        if (produtos.isEmpty()){
            return null;
        }
        Double precoTotal = 0.0;
        for(Produto produto : produtos){
            precoTotal += produto.getValor();
        }
        return precoTotal;
    }
}
