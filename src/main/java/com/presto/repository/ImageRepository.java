package com.presto.repository;

import com.presto.model.ImageModel;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

public interface ImageRepository extends JpaRepositoryImplementation<ImageModel, Long> {
    Optional<ImageModel> findByName(String name);
}
