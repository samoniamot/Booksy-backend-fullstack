package com.booksy.controller;

import com.booksy.model.Libro;
import com.booksy.repository.LibroRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@Tag(name = "libros", description = "endpoints para gestionar el catalogo de libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;
    
    @Operation(summary = "obtener todos los libros")
    @GetMapping
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }
    
    @Operation(summary = "obtener un libro por su id")
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerPorId(@PathVariable String id) {
        Optional<Libro> libro = libroRepository.findById(id);
        if (libro.isPresent()) {
            return ResponseEntity.ok(libro.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    @Operation(summary = "buscar libros por titulo")
    @GetMapping("/buscar")
    public List<Libro> buscarPorTitulo(@RequestParam String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }
    
    @Operation(summary = "crear un nuevo libro", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroRepository.save(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLibro);
    }
    
    @Operation(summary = "actualizar un libro existente", security = @SecurityRequirement(name = "bearerAuth"))
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
    
    @Operation(summary = "eliminar un libro", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable String id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
