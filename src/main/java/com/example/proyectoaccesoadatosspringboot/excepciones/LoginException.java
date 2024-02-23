package com.example.proyectoaccesoadatosspringboot.excepciones;

public class LoginException extends RuntimeException {

    public LoginException(String mensaje) {
        super(mensaje);
    }
}