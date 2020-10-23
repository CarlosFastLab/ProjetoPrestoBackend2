package com.presto.repository;

import com.presto.model.Imagem;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

public interface ImagemRepository extends JpaRepositoryImplementation<Imagem, Long> {
}
