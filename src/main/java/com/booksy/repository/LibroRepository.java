package com.booksy.repository;

import com.booksy.model.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * repositorio para acceder a los libros en mongodb
 */
@Repository
public interface LibroRepository extends MongoRepository<Libro, String> {
    
    // buscar libros por titulo (contiene el texto)
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
}
