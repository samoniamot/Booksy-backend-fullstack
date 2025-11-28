package com.booksy.config;

import com.booksy.model.Libro;
import com.booksy.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    
    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;
    
    @Override
    public void run(String... args) throws Exception {
        // solo agregar datos si la base esta vacia
        if (libroRepository.count() == 0) {
            System.out.println("Inicializando datos de libros...");
            
            String imgBase = baseUrl + "/images/";
            
            // agregar libros clasicos con imagenes locales
            libroRepository.save(new Libro(
                "Los Hermanos Karamazov",
                "La ultima novela de Dostoyevski, una obra maestra sobre la fe, la duda y la redencion",
                imgBase + "hermanos-karamazov.jpg",
                26990
            ));
            
            libroRepository.save(new Libro(
                "Anna Karenina",
                "Clasico de Leon Tolstoi sobre el amor prohibido y la sociedad rusa del siglo XIX",
                imgBase + "anna-karenina.jpg",
                17990
            ));
            
            libroRepository.save(new Libro(
                "Cuentos de Edgar Allan Poe",
                "Relatos extraordinarios del maestro del terror y el misterio",
                imgBase + "edgar-allan-poe.jpg",
                17990
            ));
            
            libroRepository.save(new Libro(
                "Cumbres Borrascosas",
                "La apasionante historia de amor de Emily Bronte ambientada en los paramos ingleses",
                imgBase + "cumbres-borrascosas.jpg",
                17990
            ));
            
            libroRepository.save(new Libro(
                "H.P. Lovecraft - Antologia del Terror",
                "Los mejores relatos cosmicos del creador de Cthulhu y los Mitos",
                imgBase + "lovecraft.jpg",
                17990
            ));
            
            libroRepository.save(new Libro(
                "Don Quijote de la Mancha",
                "La obra cumbre de Cervantes y de la literatura universal",
                imgBase + "don-quijote.jpg",
                29990
            ));
            
            libroRepository.save(new Libro(
                "Alicia en el Pais de las Maravillas",
                "El clasico de Lewis Carroll sobre el viaje fantastico de Alicia",
                imgBase + "alicia-maravillas.jpg",
                15990
            ));
            
            libroRepository.save(new Libro(
                "La Divina Comedia",
                "El viaje de Dante por el Infierno, Purgatorio y Paraiso",
                imgBase + "divina-comedia.jpg",
                26990
            ));
            
            libroRepository.save(new Libro(
                "Peter Pan",
                "La magica historia del nino que nunca crece de J.M. Barrie",
                imgBase + "peter-pan.jpg",
                15990
            ));
            
            libroRepository.save(new Libro(
                "El Conde de Montecristo",
                "La historia de venganza y redencion de Alejandro Dumas",
                imgBase + "conde-montecristo.jpg",
                26990
            ));
            
            System.out.println("Datos inicializados correctamente!");
        }
    }
}
