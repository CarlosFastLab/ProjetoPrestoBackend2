package com.presto.repository;

import com.presto.model.Cardapio;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;


public interface CardapioRepository extends JpaRepositoryImplementation<Cardapio, Long> {

    Optional<Cardapio> findByNomeContaining(String nome);
}
