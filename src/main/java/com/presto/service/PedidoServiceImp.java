package com.presto.service;

import com.presto.model.Pedido;
import com.presto.model.Produto;

import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class PedidoServiceImp implements PedidoService {


    @Override
    public Double somarTodal(List<Produto> produtos) {
        if (produtos.isEmpty()){
            return null;
        }
        double precoTotal = 0.0;
        for(Produto produto : produtos){
            precoTotal= precoTotal + produto.getValor();
            System.out.println(produto.getValor());
        }

        System.out.println(precoTotal);
        return precoTotal;
    }

    @Override
    public long pegarMaiorTempo(List<Produto> produtos) {
        long maiorTempo = 0;
        for (Produto produto : produtos){

            if(produto.getTempo() > maiorTempo){
                maiorTempo = produto.getTempo();
            }

        }

        return maiorTempo;
    }




}
