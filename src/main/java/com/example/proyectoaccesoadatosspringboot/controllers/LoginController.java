package com.example.proyectoaccesoadatosspringboot.controllers;

import com.example.proyectoaccesoadatosspringboot.Sesion;
import com.example.proyectoaccesoadatosspringboot.controllers.servicios.LoginService;
import com.example.proyectoaccesoadatosspringboot.excepciones.LoginException;
import com.example.proyectoaccesoadatosspringboot.modelos.Alumno;
import com.example.proyectoaccesoadatosspringboot.repositorios.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @GetMapping("/login")
    public String loginPage( Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String loginPage(String email, String pass) {
        if (alumnoRepository.findAlumnoByEmailAndContrasena( email, pass ) == null){
            return "login";
        }
        else{
            Alumno alu = alumnoRepository.findAlumnoByEmailAndContrasena( email, pass );
            Sesion.login( alu );
            return "redirect:./listaActividades/" + alu.getIdalumno();
        }

    }

}