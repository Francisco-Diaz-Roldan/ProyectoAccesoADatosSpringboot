package com.example.proyectoaccesoadatosspringboot.repositorios;

import com.example.proyectoaccesoadatosspringboot.modelos.Actividad;
import com.example.proyectoaccesoadatosspringboot.modelos.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActividadRepository extends JpaRepository<Actividad, Long> {
    @Query("SELECT a FROM Actividad a WHERE a.alumno = :alumno")
    List<Actividad> getAllByAlumno(@Param("alumno") Alumno alumno);
}