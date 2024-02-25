package com.example.proyectoaccesoadatosspringboot.util;

import java.io.IOException;

/**
 * Clase de utilidad para lanzar el navegador web predeterminado en el sistema operativo del usuario.
 */
public class Utility {
    /**
     * Método para lanzar el navegador web predeterminado.
     * Abre el navegador web en la URL proporcionada.
     */
    public static void launchWeb(){
        //Obtengo el nombre del Sistema Operativo
        String os = System.getProperty("os.name").toLowerCase();
        try{
            // Abro el navegador web según el Sistema Operativo
            if (os.contains("win")){
                // En Windows, abre Google Chrome
                Runtime.getRuntime().exec("cmd /c start chrome http://localhost:8080/api/login");
            }else{
                // En otros sistemas operativos (por ejemplo, macOS), abre Safari
                Runtime.getRuntime().exec("open -a Safari http://localhost:8080/api/login");
            }
        } catch (IOException e) {
            // Captura y relanza cualquier excepción de E/S como una RuntimeException
            throw new RuntimeException(e);
        }
    }
}