package com.example.proyectoaccesoadatosspringboot.repositorios;

import com.example.proyectoaccesoadatosspringboot.modelos.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz de repositorio para la entidad Profesor.
 */
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    /**
     * Obtiene un profesor por su ID.
     *
     * @param idprofesor ID del profesor.
     * @return El profesor encontrado o null si no se encuentra ninguno.
     */
    @Query("SELECT p FROM Profesor p WHERE p.idprofesor = :idprofesor")
    Profesor getById(@Param("idprofesor") Long idprofesor);
}
