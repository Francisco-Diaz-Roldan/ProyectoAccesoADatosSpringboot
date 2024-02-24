package com.example.proyectoaccesoadatosspringboot;

import com.example.proyectoaccesoadatosspringboot.modelos.Alumno;
import lombok.Data;
import lombok.Getter;

@Data
public class Sesion {
    @Getter
    private static Alumno alumnoLogeado;

    public static void login(Alumno alumno){
        alumnoLogeado = alumno;
    }
    public static void logout(){
        alumnoLogeado = null;
    }

}
