package com.example.proyectoaccesoadatosspringboot.modelos;

import com.example.proyectoaccesoadatosspringboot.enumerados.Tipo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * Clase que representa una actividad realizada por un alumno.
 */
@Data
@Entity
@Table(name = "actividad")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idactividad;
    private String fecha;
    private String observacion;
    private Integer horas;
    private String tipo;
    private String activity;
    @ManyToOne
    @JoinColumn(name = "alumno", referencedColumnName = "idalumno")
    private Alumno alumno;

    /**
     * Método toString para representar la actividad como una cadena de texto.
     * @return Representación de la actividad en forma de cadena de texto.
     */
    @Override
    public String toString( ) {
        return "Actividad{" +
                "idactividad=" + idactividad +
                ", fecha=" + fecha +
                ", observacion='" + observacion + '\'' +
                ", horas=" + horas +
                ", tipo='" + tipo + '\'' +
                ", actividad='" + activity + '\'' +
                ", alumno=" + alumno.getNombre() +
                '}';
    }
}
