package com.bcnc.pruebatecnica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de configuración y arranque de la aplicación Spring Boot.
 *
 * <p>Esta aplicación implementa una arquitectura hexagonal para gestionar la consulta
 * de tarifas de precios aplicables a productos de marcas específicas en rangos de fechas concretos.
 * </p>
 *
 * @author Fernando Matilla Méndez
 * @version 1.0.0
 * @since 2026-07-17
 */
@SpringBootApplication
public class PruebatecnicaApplication {
  /**
   * Punto de entrada principal para la ejecución de la aplicación.
   * Inicializa el contexto de Spring, arranca el servidor embebido (Tomcat)
   * y carga las configuraciones necesarias de la base de datos H2 en memoria.
   *
   * @param args argumentos de línea de comandos proporcionados al arrancar la aplicación.
   */
  public static void main(String[] args) {
    SpringApplication.run(PruebatecnicaApplication.class, args);
  }

}
