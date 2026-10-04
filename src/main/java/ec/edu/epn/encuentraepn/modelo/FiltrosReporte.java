package ec.edu.epn.encuentraepn.modelo;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Criterios de búsqueda del mapa (el parámetro «filtros» de los diagramas).
 * Un criterio en null no se aplica.
 */
public record FiltrosReporte(
        TipoReporte tipo,
        UUID categoriaId,
        LocalDate fechaDesde,
        LocalDate fechaHasta,
        String zona) {

    public static FiltrosReporte sinFiltros() {
        return new FiltrosReporte(null, null, null, null, null);
    }
}
