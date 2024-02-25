package com.example.proyectoaccesoadatosspringboot.controllers;

import com.example.proyectoaccesoadatosspringboot.Sesion;
import com.example.proyectoaccesoadatosspringboot.modelos.Actividad;
import com.example.proyectoaccesoadatosspringboot.modelos.Alumno;
import com.example.proyectoaccesoadatosspringboot.repositorios.ActividadRepository;
import com.example.proyectoaccesoadatosspringboot.repositorios.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


/**
 * Controlador para gestionar las operaciones relacionadas con las actividades.
 */
@Controller
@RequestMapping("/api")
public class ActividadController {

    @Autowired
    AlumnoRepository alumnoRepository;
    @Autowired
    ActividadRepository actividadRepository;

    /**
     * Método GET para mostrar la lista de actividades de un alumno.
     * @param idAlu ID del alumno.
     * @param model Modelo para pasar datos a la vista.
     * @return La vista "actividades" si el alumno existe, de lo contrario, redirige a la página de inicio de sesión.
     */
    @GetMapping("/listaActividades/{idAlu}")
    public String listaActividades( @PathVariable Long idAlu, Model model) {
        if(alumnoRepository.existsById( idAlu )){
            Alumno alumno = alumnoRepository.findAlumnoByIdalumno( idAlu );
            model.addAttribute("actividades",alumno.getActividad_diaria());
            return "actividades";
        }
        else{
            return "login";
        }
    }/**
     * Método GET para mostrar el formulario de creación de una nueva actividad.
     * @param model Modelo para pasar datos a la vista.
     * @return La vista "edit" para crear una nueva actividad.
     */

    @GetMapping("/new")
    public String newActivity(Model model){
        Actividad actividad = new Actividad();
        model.addAttribute("actividad", actividad);
        model.addAttribute("idAlu",Sesion.getAlumnoLogeado().getIdalumno());
        return "edit";
    }
    /**
     * Método POST para procesar la creación de una nueva actividad.
     * @param datos Datos de la actividad a crear.
     * @return Redirige a la lista de actividades del alumno actual.
     */
    @PostMapping("/new")
    public String newActivity(@ModelAttribute Actividad datos){
        datos.setAlumno( Sesion.getAlumnoLogeado() );
        actividadRepository.save( datos);

        return "redirect:/api/listaActividades/"+ Sesion.getAlumnoLogeado().getIdalumno();
    }
    /**
     * Método GET para mostrar el formulario de edición de una actividad.
     * @param id ID de la actividad a editar.
     * @param model Modelo para pasar datos a la vista.
     * @return La vista "edit" para editar la actividad si existe, de lo contrario, redirige a la página de inicio de sesión.
     */
    @GetMapping("/actividad/{id}")
    public String edit(@PathVariable Long id, Model model){
        if( actividadRepository.existsById(id)){
            model.addAttribute("actividad",actividadRepository.findById(id).get());
            model.addAttribute("idAlu",Sesion.getAlumnoLogeado().getIdalumno());
            return "edit";
        } else {
            return "redirect:/api/login";
        }
    }
    /**
     * Método POST para procesar la actualización de una actividad.
     * @param idactividad ID de la actividad a actualizar.
     * @param datos Datos actualizados de la actividad.
     * @return Redirige a la lista de actividades del alumno actual.
     */
    @PostMapping("/actividad/{idactividad}")

    public String editPost(@PathVariable Long idactividad, @ModelAttribute Actividad datos){
        datos.setAlumno( Sesion.getAlumnoLogeado() );
        actividadRepository.save(datos);
        return "redirect:/api/listaActividades/"+Sesion.getAlumnoLogeado().getIdalumno();
    }
    /**
     * Método GET para eliminar una actividad.
     * @param idactividad ID de la actividad a eliminar.
     * @return Redirige a la lista de actividades del alumno actual.
     */
    @GetMapping("/borrar/{idactividad}")
    public String deletePost(@PathVariable Long idactividad){
        Optional<Actividad> actividad = actividadRepository.findById( idactividad );
        if(actividad.isPresent( )){
            actividadRepository.delete( actividad.get() );
        }

        return "redirect:/api/listaActividades/"+Sesion.getAlumnoLogeado().getIdalumno();
    }
}
