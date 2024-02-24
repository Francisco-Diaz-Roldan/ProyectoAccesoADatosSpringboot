package com.example.proyectoaccesoadatosspringboot.repositorios;

import com.example.proyectoaccesoadatosspringboot.modelos.Actividad;
import com.example.proyectoaccesoadatosspringboot.modelos.Alumno;
import com.example.proyectoaccesoadatosspringboot.modelos.Empresa;
import com.example.proyectoaccesoadatosspringboot.modelos.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActividadRepository extends JpaRepository<Actividad, Long> {

}