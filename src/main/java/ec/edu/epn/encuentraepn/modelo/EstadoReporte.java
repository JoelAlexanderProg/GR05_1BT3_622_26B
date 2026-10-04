package ec.edu.epn.encuentraepn.modelo;

/**
 * Estados de un reporte: BORRADOR → ACTIVO → RESERVADO → ENTREGADO.
 */
public enum EstadoReporte {

    BORRADOR("Borrador"),
    ACTIVO("Activo"),
    RESERVADO("Reservado"),
    ENTREGADO("Entregado");

    private final String etiqueta;

    EstadoReporte(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}
