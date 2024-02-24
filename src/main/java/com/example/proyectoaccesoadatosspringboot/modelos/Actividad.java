package com.example.proyectoaccesoadatosspringboot.modelos;

import com.example.proyectoaccesoadatosspringboot.enumerados.Tipo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

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
