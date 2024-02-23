package com.example.proyectoaccesoadatosspringboot;

import com.example.proyectoaccesoadatosspringboot.util.Utility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoAccesoADatosSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoAccesoADatosSpringbootApplication.class, args);
        Utility.launchWeb();
    }

}
