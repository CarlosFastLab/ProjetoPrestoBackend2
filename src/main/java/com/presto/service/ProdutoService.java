package com.presto.service;

import com.presto.model.Produto;
import com.presto.model.ProdutoRetorno;

import java.util.List;

public interface ProdutoService {
    public List<ProdutoRetorno> transformarImagem(List<Produto> produtos);
}
