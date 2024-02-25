package com.example.proyectoaccesoadatosspringboot.modelos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una empresa.
 */
@Data
@Entity
@Table(name = "empresas")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idempresa;
    private Integer telefono;
    private String email;
    private String responsable;
    private String observaciones;
    private String nombre;
    @JsonIgnore
    @OneToMany(mappedBy = "empresa",fetch = FetchType.EAGER)
    private List<Alumno> alumnos = new ArrayList<>();

    /**
     * Método toString para representar la empresa como una cadena de texto.
     * @return Representación de la empresa en forma de cadena de texto.
     */
    @Override
    public String toString( ) {
        return "Empresa{" +
                "idempresa=" + idempresa +
                ", telefono=" + telefono +
                ", email='" + email + '\'' +
                ", responsable='" + responsable + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", nombre='" + nombre + '\'' +
                ", alumnos=" + alumnos.size() +
                '}';
    }
}
