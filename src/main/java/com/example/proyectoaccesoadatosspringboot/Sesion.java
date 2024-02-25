package com.example.proyectoaccesoadatosspringboot;

import com.example.proyectoaccesoadatosspringboot.modelos.Alumno;
import lombok.Data;
import lombok.Getter;

/**
 * Clase de sesión que gestiona la información del alumno logeado.
 */
@Data
public class Sesion {

    /**
     * El alumno actualmente logeado.
     */
    @Getter
    private static Alumno alumnoLogeado;

    /**
     * Método para iniciar sesión con un alumno específico.
     *
     * @param alumno El alumno que inicia sesión.
     */
    public static void login(Alumno alumno){
        alumnoLogeado = alumno;
    }

    /**
     * Método para cerrar sesión, eliminando la información del alumno logeado.
     */
    public static void logout(){
        alumnoLogeado = null;
    }

}
