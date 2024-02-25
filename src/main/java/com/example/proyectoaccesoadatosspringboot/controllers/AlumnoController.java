package com.example.proyectoaccesoadatosspringboot.controllers;

import com.example.proyectoaccesoadatosspringboot.modelos.Actividad;
import com.example.proyectoaccesoadatosspringboot.modelos.Alumno;
import com.example.proyectoaccesoadatosspringboot.modelos.Empresa;
import com.example.proyectoaccesoadatosspringboot.modelos.Profesor;
import com.example.proyectoaccesoadatosspringboot.repositorios.AlumnoRepository;
import com.example.proyectoaccesoadatosspringboot.repositorios.EmpresaRepository;
import com.example.proyectoaccesoadatosspringboot.repositorios.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para manejar operaciones relacionadas con los alumnos.
 */

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ProfesorRepository profesorRepository;


// Métodos de la API para manipular alumnos

    /**
     * Obtiene todos los alumnos.
     * @return ResponseEntity con la lista de alumnos y el estado HTTP OK si tiene éxito.
     */
    @GetMapping
    public ResponseEntity<List<Alumno>> getAllAlumnos() {
        List<Alumno> alumnos = alumnoRepository.findAll();
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

    /**
     * Obtiene un alumno por su ID.
     * @param id ID del alumno.
     * @return ResponseEntity con el alumno encontrado y el estado HTTP OK si tiene éxito, o un estado NOT_FOUND si no se encuentra el alumno.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable Long id) {
        Optional<Alumno> optionalAlumno = alumnoRepository.findById(id);

        return optionalAlumno.map(alumno -> new ResponseEntity<>(alumno, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Crea un nuevo alumno.
     * @param alumno Datos del nuevo alumno.
     * @return ResponseEntity con el alumno creado y el estado HTTP CREATED si tiene éxito.
     */
    @PostMapping
    public ResponseEntity<Alumno> createAlumno(@RequestBody Alumno alumno) {
        Alumno nuevoAlumno = alumnoRepository.save(alumno);
        return new ResponseEntity<>(nuevoAlumno, HttpStatus.CREATED);
    }

    /**
     * Actualiza un alumno existente.
     * @param id ID del alumno a actualizar.
     * @param alumno Datos actualizados del alumno.
     * @return ResponseEntity con el alumno actualizado y el estado HTTP OK si tiene éxito, o un estado NOT_FOUND si el alumno no existe.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable Long id, @RequestBody Alumno alumno) {
        if (!alumnoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        alumno.setIdalumno(id);
        Alumno updatedAlumno = alumnoRepository.save(alumno);

        return new ResponseEntity<>(updatedAlumno, HttpStatus.OK);
    }

    /**
     * Elimina un alumno por su ID.
     * @param id ID del alumno a eliminar.
     * @return ResponseEntity con estado HTTP NO_CONTENT si el alumno se elimina correctamente, o un estado NOT_FOUND si el alumno no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Long id) {
        if (!alumnoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        alumnoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Obtiene un alumno por su email.
     * @param email Email del alumno.
     * @return ResponseEntity con el alumno encontrado y el estado HTTP OK si tiene éxito, o un estado NOT_FOUND si el alumno no existe.
     */
    @GetMapping("/byEmail/{email}")
    public ResponseEntity<Alumno> getAlumnoByEmail(@PathVariable String email) {
        Alumno alumno = alumnoRepository.getByEmail(email);

        return alumno != null ?
                new ResponseEntity<>(alumno, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Obtiene una lista de alumnos asociados a una empresa por su ID.
     * @param idEmpresa ID de la empresa.
     * @return ResponseEntity con la lista de alumnos encontrados y el estado HTTP OK si tiene éxito, o un estado NOT_FOUND si la empresa no existe.
     */
    @GetMapping("/byEmpresa/{idEmpresa}")
    public ResponseEntity<List<Alumno>> getAlumnosByEmpresa(@PathVariable Long idEmpresa) {
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(idEmpresa);

        if (optionalEmpresa.isPresent()) {
            Empresa empresa = optionalEmpresa.get();
            List<Alumno> alumnos = alumnoRepository.getAllByEmpresa(empresa);
            return new ResponseEntity<>(alumnos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtiene una lista de alumnos asociados a una empresa por su nombre.
     * @param nombreEmpresa Nombre de la empresa.
     * @return ResponseEntity con la lista de alumnos encontrados y el estado HTTP OK si tiene éxito.
     */
    @GetMapping("/byNombreEmpresa/{nombreEmpresa}")
    public ResponseEntity<List<Alumno>> getAlumnosByNombreEmpresa(@PathVariable String nombreEmpresa) {
        List<Alumno> alumnos = alumnoRepository.getAllByNombreEmpresa(nombreEmpresa);
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

    /**
     * Obtiene una lista de alumnos asociados a un tutor por su ID.
     * @param idTutor ID del tutor.
     * @return ResponseEntity con la lista de alumnos encontrados y el estado HTTP OK si tiene éxito, o un estado NOT_FOUND si el tutor no existe.
     */
    @GetMapping("/byTutor/{idTutor}")
    public ResponseEntity<List<Alumno>> getAlumnosByTutor(@PathVariable Long idTutor) {
        Optional<Profesor> optionalProfesor = profesorRepository.findById(idTutor);

        if (optionalProfesor.isPresent()) {
            Profesor tutor = optionalProfesor.get();
            List<Alumno> alumnos = alumnoRepository.getAllByTutor(tutor);
            return new ResponseEntity<>(alumnos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtiene todas las actividades de un alumno por su ID.
     * @param idAlumno ID del alumno.
     * @return ResponseEntity con la lista de actividades encontradas y el estado HTTP OK si tiene éxito, o un estado NOT_FOUND si el alumno no existe.
     */
    @GetMapping("/{idAlumno}/actividades")
    public ResponseEntity<List<Actividad>> getActividadesByAlumno(@PathVariable Long idAlumno) {
        Optional<Alumno> optionalAlumno = alumnoRepository.findById(idAlumno);

        if (optionalAlumno.isPresent()) {
            Alumno alumno = optionalAlumno.get();
            List<Actividad> actividades = alumno.getActividad_diaria();
            return new ResponseEntity<>(actividades, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtiene una lista de alumnos asociados a una actividad por su ID.
     * @param idActividad ID de la actividad.
     * @return ResponseEntity con la lista de alumnos encontrados y el estado HTTP OK si tiene éxito.
     */
    @GetMapping("/byActividad/{idActividad}")
    public ResponseEntity<List<Alumno>> getAlumnosByActividad(@PathVariable Long idActividad) {
        // Utiliza el repositorio de alumnos para obtener la lista de alumnos asociados a la actividad
        List<Alumno> alumnos = alumnoRepository.getAllByActividad(idActividad);

        // Devuelve la lista de alumnos en la respuesta HTTP con el código de estado OK
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }
}