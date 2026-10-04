package ec.edu.epn.encuentraepn.web;

import ec.edu.epn.encuentraepn.controlador.ControlBase;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Exige que el usuario esté identificado antes de registrar un reporte, reclamar
 * un objeto o gestionar una devolución. Si no lo está, lo envía a identificarse
 * y después lo devuelve a la página que pidió.
 */
@WebFilter({"/reportes/nuevo", "/reclamaciones/*", "/devoluciones"})
public class FiltroIdentificacion extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain cadena)
            throws IOException, ServletException {
        HttpSession sesion = request.getSession(false);
        if (sesion != null && sesion.getAttribute(ControlBase.USUARIO_EN_SESION) != null) {
            cadena.doFilter(request, response);
            return;
        }
        String destino = request.getServletPath()
                + (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        response.sendRedirect(request.getContextPath() + "/identificacion?destino="
                + URLEncoder.encode(destino, StandardCharsets.UTF_8));
    }
}
