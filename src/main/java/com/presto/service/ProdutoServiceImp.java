package com.presto.service;

import com.presto.model.Produto;
import com.presto.model.ProdutoRetorno;

import java.util.List;

public class ProdutoServiceImp implements ProdutoService {

    @Override
    public List<ProdutoRetorno> transformarImagem(List<Produto> produtos) {
        for(Produto produto : produtos){
        }
        return null;
    }
}
