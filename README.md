# GR05_1BT3_622_26B: EncuentraEPN

Sistema web georreferenciado de objetos perdidos y encontrados en el campus de la Escuela Politécnica Nacional.

Tarea 3 de Metodologías Ágiles (ISWD622, EPN, 2026-B), Grupo 5: implementación en Java Web de los dos incrementos funcionales modelados con el Proceso Unificado en la Tarea 1.

## Casos de uso implementados

| Incremento | Caso de uso | Control (Servlet) | Vistas (JSP) |
|---|---|---|---|
| 1 | CU-01 Registrar objeto perdido o encontrado | `ControlRegistroReporte` | `formularioReporte.jsp` |
| 1 | CU-02 Consultar y filtrar reportes en el mapa | `ControlConsultaReportes` | `mapaReportes.jsp`, `detalleReporte.jsp` |
| 2 | CU-03 Reclamar un objeto encontrado | `ControlReclamaciones` | `formularioReclamacion.jsp` |
| 2 | CU-04 Gestionar devolución del objeto | `ControlDevoluciones` | `panelDevolucion.jsp`, `vistaAcuerdoEntrega.jsp` |

## Trazabilidad de los modelos al código

El código conserva los nombres de los diagramas de la Tarea 1:

| En los modelos | En el código |
|---|---|
| Clases «entity» (`Reporte`, `Reclamacion`, `AcuerdoEntrega`…) | Entidades JPA del paquete `modelo`, con los mismos atributos y operaciones |
| Clases «control» (`ControlRegistroReporte`…) | Servlets del paquete `controlador`, con las mismas operaciones |
| Clases «boundary» (`FormularioReporte`, `MapaReportes`…) | Páginas JSP de `WEB-INF/vistas` con el mismo nombre |
| Mensajes de los diagramas de secuencia (SEC-01 a SEC-04) | Comentarios `// SEC-0n · número de mensaje` junto a la línea que realiza cada mensaje |

Los diagramas de diseño, ya adaptados a Java Web, están en [`docs/modelos`](docs/modelos): las fuentes PlantUML en `src` y las imágenes en `img`.

## Tecnologías

| Capa | Tecnología |
|---|---|
| Vista | JSP con JSTL; mapa con Leaflet y OpenStreetMap |
| Controlador | Servlets (Jakarta Servlet 6) |
| Persistencia (ORM) | JPA con Hibernate |
| Base de datos | H2 embebida (archivo en `~/gr05_1bt3/`) |
| Servidor | Apache Tomcat 10.1 |
| Construcción | Maven |

No se usan frameworks de aplicación.

## Estructura

```
src/main/java/ec/edu/epn/encuentraepn/
├── modelo/        entidades JPA, enumeraciones y datos de apoyo (Campus, DatosReporte, FiltrosReporte, DatosPublicos)
├── dao/           JPAUtil, GenericDAO<T> y un DAO por entidad raíz
├── controlador/   un Servlet por clase de control, más la identificación y la página de inicio
└── web/           filtros y carga inicial de categorías
src/main/webapp/
├── WEB-INF/vistas/   una JSP por clase de interfaz
├── css/, js/         estilos y mapa
└── lib/leaflet/      librería del mapa
docs/modelos/         diagramas de clases y de secuencia del diseño
```

## Cómo ejecutar

Solo se necesita Java 17 o superior. Maven y Tomcat se descargan automáticamente la primera vez.

```bash
# Windows (PowerShell o símbolo del sistema)
.\mvnw.cmd package cargo:run

# Linux o macOS
./mvnw package cargo:run
```

Luego abrir <http://localhost:8080/GR05_1BT3_622_26B/>. Para detener el servidor, presionar `Ctrl + C`.

Para probar el flujo completo se necesitan dos usuarios, por ejemplo en dos navegadores: uno registra un objeto encontrado y el otro lo reclama. La identificación pide solo el nombre y un correo `@epn.edu.ec`.

## Integrantes

- Lenin Alejandro Jerez Chimbo
- Dylan Isaí Maldonado Morales
- Joel Alexander Places Lucero
- Jeimy Jhair Sánchez Tixe
- Francisco Gabriel Villalba Portilla
