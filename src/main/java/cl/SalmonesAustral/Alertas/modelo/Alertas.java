package cl.SalmonesAustral.Alertas.modelo;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "alertas")
public class Alertas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mortalidad_id")
    private Long mortalidadId;

    @Column(name = "jaula_id")
    private int jaulaId;

    @Column(name = "mensaje")
    private String mensaje;

    // BAJO - MEDIO - ALTO - CRITICO
    @Column(name = "nivel")
    private String nivel;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    // ACTIVA - RESUELTA
    @Column(name = "estado")
    private String estado;

    @Column(name = "porcentaje")
    private double porcentaje;

    public Alertas() {}

    // GETTERS Y SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getMortalidadId() { return mortalidadId; }
    public void setMortalidadId(Long mortalidadId) { this.mortalidadId = mortalidadId; }
    public int getJaulaId() { return jaulaId; }
    public void setJaulaId(int jaulaId) { this.jaulaId = jaulaId; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }
}