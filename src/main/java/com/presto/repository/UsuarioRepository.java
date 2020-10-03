package com.presto.repository;


import com.presto.model.Usuario;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UsuarioRepository extends JpaRepositoryImplementation<Usuario, Long> {
   Optional<Usuario> findByEmailContaining(String email);
}
