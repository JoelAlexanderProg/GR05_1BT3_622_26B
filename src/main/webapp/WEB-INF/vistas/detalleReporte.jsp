<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%-- «boundary» DetalleReporte (CU-02): mostrarDetalle(datosPublicos) --%>
<c:set var="titulo" value="Detalle del reporte"/>
<%@ include file="/WEB-INF/vistas/comun/cabecera.jspf" %>

<h1>Detalle del reporte</h1>

<%-- mostrarDetalle(datosPublicos): solo información pública --%>
<div class="dos-columnas">
    <dl class="datos" id="detalle">
        <dt>Tipo</dt>
        <dd><span class="etiqueta ${fn:toLowerCase(detalle.tipo)}">${detalle.tipo.etiqueta}</span></dd>
        <dt>Categoría</dt><dd><c:out value="${detalle.categoria}"/></dd>
        <dt>Descripción</dt><dd><c:out value="${detalle.descripcionPublica}"/></dd>
        <dt>Fecha</dt><dd>${detalle.fechaSuceso}</dd>
        <dt>Ubicación</dt><dd>Zona ${detalle.zona} (${detalle.latitud}, ${detalle.longitud})</dd>
        <dt>Estado</dt><dd>${detalle.estado.etiqueta}</dd>
    </dl>
    <%@ include file="/WEB-INF/vistas/comun/mapa.jspf" %>
</div>

<div class="acciones">
    <a href="${ctx}/reportes">Volver al mapa</a>
</div>

<script>
    const mapa = crearMapaCampus();
    crearMarcador(${detalle.latitud}, ${detalle.longitud}, '${detalle.tipo}').addTo(mapa);
</script>

<%@ include file="/WEB-INF/vistas/comun/pie.jspf" %>
