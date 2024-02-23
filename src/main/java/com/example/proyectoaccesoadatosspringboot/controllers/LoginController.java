package com.example.proyectoaccesoadatosspringboot.controllers;

import com.example.proyectoaccesoadatosspringboot.modelos.Alumno;
import com.example.proyectoaccesoadatosspringboot.repositorios.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody Alumno alumno) {
        String email = alumno.getEmail();
        String contrasenya = alumno.getContrasena();

        // Busco al alumno por el correo electrónico
        Alumno alumnoEncontrado = alumnoRepository.getByEmail(email);

        // Compruebo que el alumno exista y si coincide la contraseña
        if (alumnoEncontrado != null && alumnoEncontrado.getContrasena().equals(contrasenya)) {
            return new ResponseEntity<>("Login exitoso", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Contraseña no válida", HttpStatus.UNAUTHORIZED);
        }
    }
}