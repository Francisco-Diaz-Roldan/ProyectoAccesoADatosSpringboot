package com.example.proyectoaccesoadatosspringboot.controllers;

import com.example.proyectoaccesoadatosspringboot.modelos.Actividad;
import com.example.proyectoaccesoadatosspringboot.modelos.Alumno;
import com.example.proyectoaccesoadatosspringboot.modelos.Empresa;
import com.example.proyectoaccesoadatosspringboot.modelos.Profesor;
import com.example.proyectoaccesoadatosspringboot.repositorios.ActividadRepository;
import com.example.proyectoaccesoadatosspringboot.repositorios.AlumnoRepository;
import com.example.proyectoaccesoadatosspringboot.repositorios.EmpresaRepository;
import com.example.proyectoaccesoadatosspringboot.repositorios.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ProfesorRepository profesorRepository;


    // Listo a  todos los Alumnos
    @GetMapping
    public ResponseEntity<List<Alumno>> getAllAlumnos() {
        List<Alumno> alumnos = alumnoRepository.findAll();
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

    // Obtengo un Alumno por ID
    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable Long id) {
        Optional<Alumno> optionalAlumno = alumnoRepository.findById(id);

        return optionalAlumno.map(alumno -> new ResponseEntity<>(alumno, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Creo un Nuevo Alumno
    @PostMapping
    public ResponseEntity<Alumno> createAlumno(@RequestBody Alumno alumno) {
        Alumno nuevoAlumno = alumnoRepository.save(alumno);
        return new ResponseEntity<>(nuevoAlumno, HttpStatus.CREATED);
    }

    // Actualizo un Alumno
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable Long id, @RequestBody Alumno alumno) {
        if (!alumnoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        alumno.setIdalumno(id);
        Alumno updatedAlumno = alumnoRepository.save(alumno);

        return new ResponseEntity<>(updatedAlumno, HttpStatus.OK);
    }

    // Elimino un Alumno
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Long id) {
        if (!alumnoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        alumnoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Obtengo un Alumno por Email
    @GetMapping("/byEmail/{email}")
    public ResponseEntity<Alumno> getAlumnoByEmail(@PathVariable String email) {
        Alumno alumno = alumnoRepository.getByEmail(email);

        return alumno != null ?
                new ResponseEntity<>(alumno, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Obtengo un Alumno por el id de su Empresa
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

    // Obteno Alumnos por Nombre de Empresa
    @GetMapping("/byNombreEmpresa/{nombreEmpresa}")
    public ResponseEntity<List<Alumno>> getAlumnosByNombreEmpresa(@PathVariable String nombreEmpresa) {
        List<Alumno> alumnos = alumnoRepository.getAllByNombreEmpresa(nombreEmpresa);
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

    // Obtengo Alumnos por Tutor
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

    // Obtengo todas las Actividades de un Alumno
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

    // Obtengo todos los alumnos que están realizando una actividad específica
    @GetMapping("/byActividad/{idActividad}")
    public ResponseEntity<List<Alumno>> getAlumnosByActividad(@PathVariable Long idActividad) {
        // Utiliza el repositorio de alumnos para obtener la lista de alumnos asociados a la actividad
        List<Alumno> alumnos = alumnoRepository.getAllByActividad(idActividad);

        // Devuelve la lista de alumnos en la respuesta HTTP con el código de estado OK
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }
}