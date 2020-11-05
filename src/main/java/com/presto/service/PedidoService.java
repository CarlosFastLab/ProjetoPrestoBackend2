package com.presto.service;

import com.presto.model.Produto;


import java.util.List;

public interface PedidoService {
    Double somarTodal(List<Produto> produtos);
}
