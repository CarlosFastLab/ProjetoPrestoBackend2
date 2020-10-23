package com.presto.repository;

import com.presto.model.Mesa;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

public interface MesaRepository extends JpaRepositoryImplementation<Mesa, Long> {
    Optional<Mesa> findByNomeContaining(String nome);
}
