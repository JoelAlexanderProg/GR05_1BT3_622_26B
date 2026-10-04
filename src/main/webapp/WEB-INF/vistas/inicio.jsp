<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:set var="titulo" value="Inicio"/>
<%@ include file="/WEB-INF/vistas/comun/cabecera.jspf" %>

<h1>Objetos perdidos y encontrados en el campus</h1>
<p>Registre un objeto que perdió o que encontró, búsquelo en el mapa del campus y coordine su devolución.</p>
<div class="acciones">
    <a class="boton" href="${ctx}/reportes">Ver mapa de reportes</a>
    <a class="boton secundario" href="${ctx}/reportes/nuevo">Registrar reporte</a>
</div>

<c:if test="${not empty sessionScope.usuario}">
    <h2>Mis reportes</h2>
    <c:choose>
        <c:when test="${empty misReportes}">
            <p class="vacio">Todavía no ha registrado reportes.</p>
        </c:when>
        <c:otherwise>
            <table id="misReportes">
                <thead>
                <tr><th>Tipo</th><th>Descripción</th><th>Fecha</th><th>Estado</th><th></th></tr>
                </thead>
                <tbody>
                <c:forEach var="reporte" items="${misReportes}">
                    <tr>
                        <td><span class="etiqueta ${fn:toLowerCase(reporte.tipo)}">${reporte.tipo.etiqueta}</span></td>
                        <td><c:out value="${reporte.descripcionPublica}"/></td>
                        <td>${reporte.fechaSuceso}</td>
                        <td>${reporte.estado.etiqueta}</td>
                        <td class="enlaces">
                            <a href="${ctx}/reportes/detalle?id=${reporte.id}">Detalle</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

</c:if>

<%@ include file="/WEB-INF/vistas/comun/pie.jspf" %>
