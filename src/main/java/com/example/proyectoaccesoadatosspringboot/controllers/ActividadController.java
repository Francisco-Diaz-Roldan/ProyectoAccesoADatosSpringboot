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

@Controller
@RequestMapping("/api")
public class ActividadController {

    @Autowired
    AlumnoRepository alumnoRepository;
    @Autowired
    ActividadRepository actividadRepository;

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
    }

    @GetMapping("/new")
    public String newJuego(Model model){
        Actividad actividad = new Actividad();
        model.addAttribute("actividad", actividad);
        model.addAttribute("idAlu",Sesion.getAlumnoLogeado().getIdalumno());
        return "edit";
    }

    @PostMapping("/new")
    public String newJuego(@ModelAttribute Actividad datos){
        datos.setAlumno( Sesion.getAlumnoLogeado() );
        actividadRepository.save( datos);

        return "redirect:/api/listaActividades/"+ Sesion.getAlumnoLogeado().getIdalumno();
    }

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

    @PostMapping("/actividad/{idactividad}")
    /**
     * Recibe los datos del formulario y actualiza
     */
    public String editPost(@PathVariable Long idactividad, @ModelAttribute Actividad datos){
        datos.setAlumno( Sesion.getAlumnoLogeado() );
        actividadRepository.save(datos);
        return "redirect:/api/listaActividades/"+Sesion.getAlumnoLogeado().getIdalumno();
    }

    @GetMapping("/borrar/{idactividad}")
    public String deletePost(@PathVariable Long idactividad){
        Optional<Actividad> actividad = actividadRepository.findById( idactividad );
        if(actividad.isPresent( )){
            actividadRepository.delete( actividad.get() );
        }

        return "redirect:/api/listaActividades/"+Sesion.getAlumnoLogeado().getIdalumno();
    }
}
