package ec.edu.epn.encuentraepn.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Solicitud de un usuario para recuperar un objeto encontrado.
 * Diagrama de clases: «entity» Reclamacion.
 */
@Entity
@Table(name = "reclamacion")
public class Reclamacion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 12)
    private EstadoReclamacion estado;

    @ManyToOne(optional = false)
    private Usuario reclamante;

    @ManyToOne(optional = false)
    private Reporte objeto;

    @OneToMany(mappedBy = "reclamacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RespuestaVerificacion> respuestas = new ArrayList<>();

    @OneToOne(mappedBy = "reclamacion", cascade = CascadeType.ALL)
    private AcuerdoEntrega acuerdo;

    protected Reclamacion() {
    }

    // SEC-03 · 14: «create» Reclamacion(actor, reporte, PENDIENTE)
    public Reclamacion(Usuario reclamante, Reporte objeto) {
        this.reclamante = reclamante;
        this.objeto = objeto;
        this.estado = EstadoReclamacion.PENDIENTE;
        this.fechaCreacion = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    }

    // SEC-03 · 15: «create» respuestas privadas (reclamacion, pregunta, texto)
    public void agregarRespuesta(PreguntaVerificacion pregunta, String textoPrivado) {
        respuestas.add(new RespuestaVerificacion(this, pregunta, textoPrivado));
    }

    // SEC-04 · 16: aceptar()
    public void aceptar() {
        exigirEstado(EstadoReclamacion.PENDIENTE);
        estado = EstadoReclamacion.ACEPTADA;
    }

    // SEC-04 · 23: el acuerdo creado queda asociado a la reclamación (asociación reclamación / acuerdo)
    public void registrarAcuerdo(AcuerdoEntrega acuerdo) {
        exigirEstado(EstadoReclamacion.ACEPTADA);
        this.acuerdo = acuerdo;
    }

    // SEC-04 · 42: completar()
    public void completar() {
        exigirEstado(EstadoReclamacion.ACEPTADA);
        estado = EstadoReclamacion.COMPLETADA;
    }

    private void exigirEstado(EstadoReclamacion esperado) {
        if (estado != esperado) {
            throw new IllegalStateException("La reclamación debe estar en estado " + esperado + " y está " + estado);
        }
    }

    public boolean estaPendiente() {
        return estado == EstadoReclamacion.PENDIENTE;
    }

    public boolean estaAceptada() {
        return estado == EstadoReclamacion.ACEPTADA;
    }

    public boolean esReclamante(Usuario usuario) {
        return reclamante.equals(usuario);
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public EstadoReclamacion getEstado() {
        return estado;
    }

    public Usuario getReclamante() {
        return reclamante;
    }

    public Reporte getObjeto() {
        return objeto;
    }

    public List<RespuestaVerificacion> getRespuestas() {
        return Collections.unmodifiableList(respuestas);
    }

    public AcuerdoEntrega getAcuerdo() {
        return acuerdo;
    }
}
