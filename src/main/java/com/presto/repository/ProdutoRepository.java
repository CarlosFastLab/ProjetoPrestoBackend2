package com.presto.repository;

import com.presto.model.Produto;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepositoryImplementation<Produto, Long> {
    List<Produto> findByTipoContaining(String tipo);
    Optional<Produto> findByNomeContaining(String nome);
}
