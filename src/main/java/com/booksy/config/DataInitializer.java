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
            
            // agregar libros clasicos con imagenes reales
            libroRepository.save(new Libro(
                "Los Hermanos Karamazov",
                "La ultima novela de Dostoyevski, una obra maestra sobre la fe, la duda y la redencion",
                "https://www.antartica.cl/media/catalog/product/9/7/9788417928896_1.jpg",
                26990
            ));
            
            libroRepository.save(new Libro(
                "Anna Karenina",
                "Clasico de Leon Tolstoi sobre el amor prohibido y la sociedad rusa del siglo XIX",
                "https://www.antartica.cl/media/catalog/product/9/7/9788497945387_1.jpg",
                17990
            ));
            
            libroRepository.save(new Libro(
                "Cuentos de Edgar Allan Poe",
                "Relatos extraordinarios del maestro del terror y el misterio",
                "https://www.antartica.cl/media/catalog/product/9/7/9788497944724_1.jpg",
                17990
            ));
            
            libroRepository.save(new Libro(
                "Cumbres Borrascosas",
                "La apasionante historia de amor de Emily Bronte ambientada en los paramos ingleses",
                "https://www.antartica.cl/media/catalog/product/9/7/9788497944632_1.jpg",
                17990
            ));
            
            libroRepository.save(new Libro(
                "H.P. Lovecraft - Antologia del Terror",
                "Los mejores relatos cosmicos del creador de Cthulhu y los Mitos",
                "https://www.antartica.cl/media/catalog/product/9/7/9788497944779_1.jpg",
                17990
            ));
            
            libroRepository.save(new Libro(
                "Don Quijote de la Mancha",
                "La obra cumbre de Cervantes y de la literatura universal",
                "https://www.antartica.cl/media/catalog/product/9/7/9788419087003_1.jpg",
                29990
            ));
            
            libroRepository.save(new Libro(
                "Alicia en el Pais de las Maravillas",
                "El clasico de Lewis Carroll sobre el viaje fantastico de Alicia",
                "https://www.antartica.cl/media/catalog/product/9/7/9788417928865_1.jpg",
                15990
            ));
            
            libroRepository.save(new Libro(
                "La Divina Comedia",
                "El viaje de Dante por el Infierno, Purgatorio y Paraiso",
                "https://www.antartica.cl/media/catalog/product/9/7/9788417477677_1.jpg",
                26990
            ));
            
            libroRepository.save(new Libro(
                "Peter Pan",
                "La magica historia del nino que nunca crece de J.M. Barrie",
                "https://www.antartica.cl/media/catalog/product/9/7/9788418211522_1.jpg",
                15990
            ));
            
            libroRepository.save(new Libro(
                "El Conde de Montecristo",
                "La historia de venganza y redencion de Alejandro Dumas",
                "https://www.antartica.cl/media/catalog/product/9/7/9788417928926_1.jpg",
                26990
            ));
            
            System.out.println("Datos inicializados correctamente!");
        }
    }
}
