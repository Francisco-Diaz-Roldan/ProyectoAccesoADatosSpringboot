package com.example.proyectoaccesoadatosspringboot.controllers.servicios;

import com.example.proyectoaccesoadatosspringboot.excepciones.LoginException;
import com.example.proyectoaccesoadatosspringboot.modelos.Alumno;
import com.example.proyectoaccesoadatosspringboot.repositorios.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio para la autenticación de usuarios.
 */
@Service
public class LoginService {

    @Autowired
    private AlumnoRepository alumnoRepository;
    /**
     * Método para verificar las credenciales del usuario.
     * @param email Correo electrónico del usuario.
     * @param contrasena Contraseña del usuario.
     * @throws LoginException Excepción lanzada si las credenciales no son válidas.
     */
    public void acreditarUsuario(String email, String contrasena) throws LoginException {
        // Busco al alumno por el correo electrónico
        Alumno alumno = alumnoRepository.getByEmail(email);

        // Compruebo que el alumno exista y que la contraseña coincida
        if (alumno != null && alumno.getContrasena().equals(contrasena)) {
        } else {
            throw new LoginException("El usuario no coincide con la contraseña");
        }
    }
}
