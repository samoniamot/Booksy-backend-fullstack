package com.booksy.controller;

import com.booksy.model.Libro;
import com.booksy.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * controlador rest para manejar los libros
 * tiene los endpoints para el crud
 */
@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "*")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;
    
    // obtener todos los libros
    @GetMapping
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }
    
    // obtener un libro por id
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerPorId(@PathVariable String id) {
        Optional<Libro> libro = libroRepository.findById(id);
        if (libro.isPresent()) {
            return ResponseEntity.ok(libro.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    // buscar libros por titulo
    @GetMapping("/buscar")
    public List<Libro> buscarPorTitulo(@RequestParam String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }
    
    // crear un nuevo libro
    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }
    
    // actualizar un libro existente
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable String id, @RequestBody Libro libroActualizado) {
        Optional<Libro> libroExistente = libroRepository.findById(id);
        if (libroExistente.isPresent()) {
            Libro libro = libroExistente.get();
            libro.setTitulo(libroActualizado.getTitulo());
            libro.setDescripcion(libroActualizado.getDescripcion());
            libro.setImagen(libroActualizado.getImagen());
            libro.setPrecio(libroActualizado.getPrecio());
            return ResponseEntity.ok(libroRepository.save(libro));
        }
        return ResponseEntity.notFound().build();
    }
    
    // eliminar un libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable String id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
