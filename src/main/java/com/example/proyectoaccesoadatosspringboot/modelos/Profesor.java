package com.example.proyectoaccesoadatosspringboot.modelos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un profesor.
 */
@Data
@Entity
@Table(name = "profesor")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idprofesor;
    private String nombre;
    private String apellidos;
    private String email;
    private String contrasenya;
    @JsonIgnore
    @OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER)
    private List<Alumno> alumnos = new ArrayList<>();

    /**
     * Método toString para representar al profesor como una cadena de texto.
     * @return Representación del profesor en forma de cadena de texto.
     */
    @Override
    public String toString( ) {
        return "Profesor{" +
                "idprofesor=" + idprofesor +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                ", alumnos=" + alumnos.size() +
                '}';
    }
}