package ec.edu.epn.encuentraepn.modelo;

/**
 * Estados de una reclamación: PENDIENTE → ACEPTADA → COMPLETADA.
 */
public enum EstadoReclamacion {

    PENDIENTE("Pendiente"),
    ACEPTADA("Aceptada"),
    COMPLETADA("Completada");

    private final String etiqueta;

    EstadoReclamacion(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}
