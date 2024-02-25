package com.example.proyectoaccesoadatosspringboot.excepciones;

/**
 * Excepción personalizada para errores relacionados con el inicio de sesión.
 */
public class LoginException extends RuntimeException {
    /**
     * Constructor que recibe un mensaje de error.
     * @param mensaje Mensaje de error que describe la causa de la excepción.
     */
    public LoginException(String mensaje) {
        super(mensaje);
    }
}