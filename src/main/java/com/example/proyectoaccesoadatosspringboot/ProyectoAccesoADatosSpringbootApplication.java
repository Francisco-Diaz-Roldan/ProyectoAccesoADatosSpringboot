package com.example.proyectoaccesoadatosspringboot;

import com.example.proyectoaccesoadatosspringboot.util.Utility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que inicia la aplicación Spring Boot y lanza el navegador web predeterminado.
 */
@SpringBootApplication
public class ProyectoAccesoADatosSpringbootApplication {

    /**
     * Método principal que inicia la aplicación Spring Boot y lanza el navegador web predeterminado.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        // Inicia la aplicación Spring Boot
        SpringApplication.run(ProyectoAccesoADatosSpringbootApplication.class, args);
        // Lanza el navegador web predeterminado para abrir la URL de la aplicación
        Utility.launchWeb();
    }

}
