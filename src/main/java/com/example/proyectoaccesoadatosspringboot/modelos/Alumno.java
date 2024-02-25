package com.example.proyectoaccesoadatosspringboot.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa a un alumno.
 */
@Data
@Entity
@Table(name = "alumno")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idalumno;
    private String nombre;
    private String apellidos;
    private String contrasena;
    private String dni;
    private Date nacimiento;
    private String email;
    private Integer telefono;
    @ManyToOne
    @JoinColumn(name = "empresa", referencedColumnName = "idempresa")
    private Empresa empresa;
    @ManyToOne
    @JoinColumn(name = "tutor", referencedColumnName = "idprofesor")
    private Profesor tutor;
    private Integer horasdual;
    private Integer horasfct;
    private String observaciones;
    @JsonIgnore
    @OneToMany(mappedBy = "alumno", fetch = FetchType.EAGER)
    private List<Actividad> actividad_diaria = new ArrayList<>();

    /**
     * Método toString para representar al alumno como una cadena de texto.
     * @return Representación del alumno en forma de cadena de texto.
     */
    @Override
    public String toString( ) {
        return "Alumno{" +
                "idalumno=" + idalumno +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", dni='" + dni + '\'' +
                ", nacimiento=" + nacimiento +
                ", email='" + email + '\'' +
                ", telefono=" + telefono +
                ", empresa=" + empresa.getNombre() +
                ", tutor=" + tutor.getNombre() +
                ", horasdual=" + horasdual +
                ", horasfct=" + horasfct +
                ", observaciones='" + observaciones + '\'' +
                ", actividad_diaria=" + actividad_diaria +
                '}';
    }
}