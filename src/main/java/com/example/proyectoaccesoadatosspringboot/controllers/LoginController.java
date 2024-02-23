package com.example.proyectoaccesoadatosspringboot.controllers;

import com.example.proyectoaccesoadatosspringboot.controllers.servicios.LoginService;
import com.example.proyectoaccesoadatosspringboot.excepciones.LoginException;
import com.example.proyectoaccesoadatosspringboot.modelos.Alumno;
import com.example.proyectoaccesoadatosspringboot.repositorios.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String loginPage() { return "login"; }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody Alumno alumno) {
        String email = alumno.getEmail();
        String contrasena = alumno.getContrasena();
        try {
            loginService.acreditarUsuario(email, contrasena);
            return new ResponseEntity<>("Login iniciado con éxito", HttpStatus.OK);
        } catch (LoginException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}