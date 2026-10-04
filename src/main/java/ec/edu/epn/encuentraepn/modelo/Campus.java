package ec.edu.epn.encuentraepn.modelo;

import java.util.List;

/**
 * Límites geográficos del campus de la EPN y sus zonas.
 * El campus se divide en tres franjas de norte a sur.
 */
public final class Campus {

    public static final double LAT_MIN = -0.2140;
    public static final double LAT_MAX = -0.2070;
    public static final double LON_MIN = -78.4930;
    public static final double LON_MAX = -78.4850;

    public static final String ZONA_NORTE = "Norte";
    public static final String ZONA_CENTRO = "Centro";
    public static final String ZONA_SUR = "Sur";
    public static final List<String> ZONAS = List.of(ZONA_NORTE, ZONA_CENTRO, ZONA_SUR);

    private Campus() {
    }

    public static boolean contiene(double latitud, double longitud) {
        return latitud >= LAT_MIN && latitud <= LAT_MAX
                && longitud >= LON_MIN && longitud <= LON_MAX;
    }

    public static String zonaDe(double latitud, double longitud) {
        double franja = (LAT_MAX - LAT_MIN) / 3;
        if (latitud >= LAT_MAX - franja) {
            return ZONA_NORTE;
        }
        if (latitud >= LAT_MIN + franja) {
            return ZONA_CENTRO;
        }
        return ZONA_SUR;
    }
}
