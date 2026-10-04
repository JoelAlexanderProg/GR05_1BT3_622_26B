package ec.edu.epn.encuentraepn.controlador;

import ec.edu.epn.encuentraepn.dao.UsuarioDAO;
import ec.edu.epn.encuentraepn.modelo.TipoReporte;
import ec.edu.epn.encuentraepn.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

/**
 * Comportamiento común de los controles: obtener el actor identificado,
 * leer identificadores, mostrar una vista y presentar los errores.
 */
public abstract class ControlBase extends HttpServlet {

    public static final String USUARIO_EN_SESION = "usuario";
    private static final String VISTAS = "/WEB-INF/vistas/";

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            super.service(request, response);
        } catch (OperacionInvalidaException e) {
            request.setAttribute("error", e.getMessage());
            mostrar("error.jsp", request, response);
        }
    }

    /**
     * Usuario identificado en la sesión (el «actor» de los diagramas), o null si no hay ninguno.
     */
    protected Usuario actor(HttpServletRequest request) {
        HttpSession sesion = request.getSession(false);
        Usuario enSesion = sesion == null ? null : (Usuario) sesion.getAttribute(USUARIO_EN_SESION);
        return enSesion == null ? null : usuarioDAO.buscarPorId(enSesion.getId());
    }

    protected Usuario actorIdentificado(HttpServletRequest request) {
        Usuario actor = actor(request);
        if (actor == null) {
            throw new OperacionInvalidaException("Identifíquese para continuar.");
        }
        return actor;
    }

    protected UUID leerId(HttpServletRequest request, String parametro) {
        UUID id = leerIdOpcional(request.getParameter(parametro));
        if (id == null) {
            throw new OperacionInvalidaException("El identificador indicado no es válido.");
        }
        return id;
    }

    /** Convierte el texto en un identificador, o devuelve null si no es válido. */
    protected UUID leerIdOpcional(String valor) {
        try {
            return UUID.fromString(valor);
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    /** Convierte el texto en un tipo de reporte, o devuelve null si no es válido. */
    protected TipoReporte leerTipo(String valor) {
        try {
            return TipoReporte.valueOf(valor);
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    /** Convierte el texto (aaaa-mm-dd) en una fecha, o devuelve null si no es válido. */
    protected LocalDate leerFecha(String valor) {
        try {
            return LocalDate.parse(valor);
        } catch (DateTimeParseException | NullPointerException e) {
            return null;
        }
    }

    protected void mostrar(String vista, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(VISTAS + vista).forward(request, response);
    }

    protected void redirigir(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws IOException {
        response.sendRedirect(request.getContextPath() + ruta);
    }
}
