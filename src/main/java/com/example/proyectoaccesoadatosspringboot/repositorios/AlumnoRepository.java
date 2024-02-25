package com.example.proyectoaccesoadatosspringboot.repositorios;

import com.example.proyectoaccesoadatosspringboot.modelos.Alumno;
import com.example.proyectoaccesoadatosspringboot.modelos.Empresa;
import com.example.proyectoaccesoadatosspringboot.modelos.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Interfaz de repositorio para la entidad Alumno.
 */
public interface AlumnoRepository  extends JpaRepository<Alumno, Long> {

    /**
     * Encuentra un alumno por correo electrónico y contraseña.
     *
     * @param email      Correo electrónico del alumno.
     * @param contrasena Contraseña del alumno.
     * @return El alumno encontrado o null si no se encuentra ninguno.
     */
    Alumno findAlumnoByEmailAndContrasena(String email, String contrasena );

    /**
     * Encuentra un alumno por su ID.
     *
     * @param idalumno ID del alumno.
     * @return El alumno encontrado o null si no se encuentra ninguno.
     */
    Alumno findAlumnoByIdalumno(Long idalumno);

    /**
     * Obtiene todos los alumnos asociados a una empresa.
     *
     * @param empresa Empresa asociada.
     * @return Lista de alumnos asociados a la empresa.
     */
    @Query("SELECT a FROM Alumno a WHERE a.empresa = :empresa")
    List<Alumno> getAllByEmpresa(@Param("empresa") Empresa empresa);

    /**
     * Obtiene todos los alumnos asociados a un tutor.
     *
     * @param tutor Tutor asociado.
     * @return Lista de alumnos asociados al tutor.
     */
    @Query("SELECT a FROM Alumno a WHERE a.tutor = :tutor")
    List<Alumno> getAllByTutor(@Param("tutor") Profesor tutor);

    /**
     * Encuentra un alumno por su correo electrónico.
     *
     * @param email Correo electrónico del alumno.
     * @return El alumno encontrado o null si no se encuentra ninguno.
     */
    @Query("SELECT a FROM Alumno a WHERE a.email = :email")
    Alumno getByEmail(@Param("email") String email);

    /**
     * Obtiene todos los alumnos asociados a una empresa por su nombre.
     *
     * @param nombreEmpresa Nombre de la empresa.
     * @return Lista de alumnos asociados a la empresa.
     */
    @Query("SELECT a FROM Alumno a WHERE a.empresa.nombre = :nombreEmpresa")
    List<Alumno> getAllByNombreEmpresa(@Param("nombreEmpresa") String nombreEmpresa);

    /**
     * Obtiene todos los alumnos que están realizando una actividad específica.
     *
     * @param idActividad ID de la actividad.
     * @return Lista de alumnos asociados a la actividad.
     */
    @Query("SELECT DISTINCT a FROM Alumno a JOIN a.actividad_diaria act WHERE act.idactividad = :idActividad")
    List<Alumno> getAllByActividad(@Param("idActividad") Long idActividad);
}
