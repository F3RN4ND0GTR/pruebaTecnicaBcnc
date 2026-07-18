# 🚀 Prueba Técnica: API de Consulta de Tarifas (Prices API)

Este proyecto implementa un microservicio REST desarrollado en **Java 17** y **Spring Boot 3** para la gestión y consulta automatizada de tarifas de precios de productos. El sistema está diseñado específicamente para resolver de forma óptima la concurrencia y prioridad de tarifas aplicables dentro de rangos de fechas específicos.

---

## 🏗️ Decisiones de Arquitectura y Diseño

### 1. Arquitectura Hexagonal (Ports & Adapters)
Para garantizar un desacoplamiento absoluto de la lógica de negocio frente a agentes externos (como la base de datos o el protocolo de exposición HTTP), el proyecto se ha estructurado siguiendo los patrones de la **Arquitectura Hexagonal**:

*   **`domain`**: El núcleo puro del negocio. Contiene el modelo inmutable `Price` y el contrato de salida (`PriceRepositoryPort`). No tiene ninguna dependencia de frameworks ni de Spring.
*   **`application`**: Orquesta los casos de uso del sistema. Implementa los puertos de entrada (`GetApplicablePriceUseCase`), gestiona los flujos de datos y expone los objetos de transporte (`PriceResponse`).
*   **`infrastructure`**: Contiene los adaptadores técnicos y de infraestructura:
    *   `adapters.in.web`: Controlador REST adaptado para interactuar vía HTTP y un gestor de excepciones unificado.
    *   `adapters.out.db`: Implementación de la persistencia utilizando Spring Data JPA sobre una base de datos en memoria **H2**, aislando el modelo relacional del modelo de dominio.

### 2. Gestión Eficiente de Prioridades y Empates
Para dar solución al requerimiento de filtrado por fechas y prioridad, se ha delegado la lógica de ordenación directamente al motor de la base de datos mediante una consulta optimizada en JPQL:
```sql
SELECT p FROM PriceEntity p 
WHERE p.productId = :productId AND p.brandId = :brandId 
  AND :applicationDate BETWEEN p.startDate AND p.endDate 
ORDER BY p.priority DESC, p.startDate DESC
```

🛠️ Tecnologías Utilizadas
Java 17 (LTS)

Spring Boot 3.x (Web, Data JPA)

H2 Database (Base de datos relacional en memoria)

Lombok (Garantía de inmutabilidad mediante @Value y minimización de código repetitivo)

OpenAPI 3 / Springdoc-openapi (Documentación interactiva de la API)

JUnit 5 & Mockito (Estrategia integral de testing automatizado)

🚀 Instalación y Ejecución
Prerrequisitos
Java Development Kit (JDK) 17 instalado.

Maven 3.x (o utilizar el wrapper ./mvnw incluido).

Pasos para arrancar la aplicación
Clona el repositorio en tu máquina local.

Compila el proyecto y ejecuta las pruebas automatizadas para validar el entorno:

Bash
./mvnw clean test
Inicia la aplicación Spring Boot:

Bash
./mvnw spring-boot:run
El microservicio estará disponible en: http://localhost:8080

📖 Documentación de la API (Swagger & H2)
Una vez que la aplicación esté en ejecución, puedes acceder a los siguientes paneles de control locales:

Swagger UI (Interactuar con el Endpoint):

👉 http://localhost:8080/swagger-ui/index.html

(Permite realizar peticiones directas desde el navegador y examinar esquemas y formatos de error)

Consola de la Base de Datos H2:

👉 http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:pricesdb

User Name: sa

Password: (Vacío)

🧪 Estrategia de Pruebas Automatizadas
El proyecto incluye dos niveles complementarios de testing que garantizan una cobertura robusta:

Tests Unitarios (PriceApplicationServiceTest): Prueban de forma aislada e instantánea la lógica de mapeo del servicio y la propagación de excepciones de negocio empleando dobles de prueba de Mockito.

Tests de Integración (PriceControllerIntegrationTest): Levantan el contexto web completo de la aplicación (MockMvc) atacando la base de datos H2 con los scripts reales de datos pre-poblados (schema.sql y data.sql). Validan con precisión quirúrgica los 5 escenarios de prueba obligatorios detallados en el enunciado del ejercicio.