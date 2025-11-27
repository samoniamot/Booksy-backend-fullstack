package com.booksy.config;

import com.booksy.model.Libro;
import com.booksy.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * clase para inicializar datos de prueba
 * se ejecuta al iniciar la aplicacion
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private LibroRepository libroRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // solo agregar datos si la base esta vacia
        if (libroRepository.count() == 0) {
            System.out.println("Inicializando datos de libros...");
            
            // agregar algunos libros de ejemplo
            libroRepository.save(new Libro(
                "Cien años de soledad",
                "La novela mas famosa de Gabriel Garcia Marquez, cuenta la historia de la familia Buendia",
                "https://images.cdn1.buscalibre.com/fit-in/360x360/85/e5/85e5e24ae498e584aa4f5e9638c681c7.jpg",
                15990
            ));
            
            libroRepository.save(new Libro(
                "Don Quijote de la Mancha",
                "La obra maestra de Miguel de Cervantes sobre el caballero andante",
                "https://images.cdn2.buscalibre.com/fit-in/360x360/fd/d0/fdd0e4f1d1af0e1a64989c60a4d9d6a8.jpg",
                12500
            ));
            
            libroRepository.save(new Libro(
                "Harry Potter y la piedra filosofal",
                "El primer libro de la saga de Harry Potter escrito por J.K. Rowling",
                "https://images.cdn3.buscalibre.com/fit-in/360x360/c0/5f/c05f3b51e24c30d6d91f4a81f41fdea0.jpg",
                18990
            ));
            
            libroRepository.save(new Libro(
                "El principito",
                "Cuento poetico de Antoine de Saint-Exupery con hermosas ilustraciones",
                "https://images.cdn1.buscalibre.com/fit-in/360x360/04/16/041688c74c26c3c87f03db91f8d7b04c.jpg",
                8990
            ));
            
            libroRepository.save(new Libro(
                "1984",
                "Novela distopica de George Orwell sobre un futuro totalitario",
                "https://images.cdn2.buscalibre.com/fit-in/360x360/59/f2/59f28db44a8e3f9d7eb8f2f47d8b9c3f.jpg",
                14500
            ));
            
            libroRepository.save(new Libro(
                "Orgullo y prejuicio",
                "Clasico de Jane Austen sobre el amor y las clases sociales en Inglaterra",
                "https://images.cdn3.buscalibre.com/fit-in/360x360/a3/b4/a3b48d5e6a9c7d3f2e1b4a5c6d7e8f9a.jpg",
                11990
            ));
            
            System.out.println("Datos inicializados correctamente!");
        }
    }
}
