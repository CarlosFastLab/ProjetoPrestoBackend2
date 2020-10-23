package com.presto.repository;

import com.presto.model.Pedido;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface PedidoRepository extends JpaRepositoryImplementation<Pedido, Long> {
}
