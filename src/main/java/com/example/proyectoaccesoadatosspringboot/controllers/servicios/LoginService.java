package com.example.proyectoaccesoadatosspringboot.controllers.servicios;

import com.example.proyectoaccesoadatosspringboot.excepciones.LoginException;
import com.example.proyectoaccesoadatosspringboot.modelos.Alumno;
import com.example.proyectoaccesoadatosspringboot.repositorios.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public void acreditarUsuario(String email, String contrasena) throws LoginException {
        // Busco al alumno por el correo electrónico
        Alumno alumno = alumnoRepository.getByEmail(email);

        // Compruebo que el alumno exista que la contraseña coincida
        if (alumno != null && alumno.getContrasena().equals(contrasena)) {
        } else {
            throw new LoginException("El usuario no coincide con la contraseña");
        }
    }
}
