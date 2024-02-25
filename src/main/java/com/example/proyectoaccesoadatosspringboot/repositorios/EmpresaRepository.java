package com.example.proyectoaccesoadatosspringboot.repositorios;

import com.example.proyectoaccesoadatosspringboot.modelos.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz de repositorio para la entidad Empresa.
 */
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    /**
     * Obtiene una empresa por su ID.
     *
     * @param idempresa ID de la empresa.
     * @return La empresa encontrada o null si no se encuentra ninguna.
     */
    @Query("SELECT e FROM Empresa e WHERE e.idempresa = :idempresa")
    Empresa getById(@Param("idempresa") Long idempresa);

}
