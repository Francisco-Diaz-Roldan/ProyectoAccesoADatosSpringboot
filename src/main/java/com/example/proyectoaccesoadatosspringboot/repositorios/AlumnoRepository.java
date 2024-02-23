package com.example.proyectoaccesoadatosspringboot.repositorios;

import com.example.proyectoaccesoadatosspringboot.modelos.Alumno;
import com.example.proyectoaccesoadatosspringboot.modelos.Empresa;
import com.example.proyectoaccesoadatosspringboot.modelos.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlumnoRepository  extends JpaRepository<Alumno, Long> {

    @Query("SELECT a FROM Alumno a WHERE a.empresa = :empresa")
    List<Alumno> getAllByEmpresa(@Param("empresa") Empresa empresa);

    @Query("SELECT a FROM Alumno a WHERE a.tutor = :tutor")
    List<Alumno> getAllByTutor(@Param("tutor") Profesor tutor);

    @Query("SELECT a FROM Alumno a WHERE a.email = :email")
    Alumno getByEmail(@Param("email") String email);

    @Query("SELECT a FROM Alumno a WHERE a.empresa.nombre = :nombreEmpresa")
    List<Alumno> getAllByNombreEmpresa(@Param("nombreEmpresa") String nombreEmpresa);
}
