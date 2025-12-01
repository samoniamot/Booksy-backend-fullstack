package com.booksy.repository;

import com.booksy.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

/**
 * repositorio para acceder a los usuarios en mongodb
 */
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    
    Optional<Usuario> findByEmail(String email);
    
    boolean existsByEmail(String email);
}
