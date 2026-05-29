package cl.SalmonesAustral.Alertas.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Alertas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación lógica con mortalidad (no directa porque es microservicio)
    private Long mortalidadId;

    private int jaulaId;

    private String mensaje;

    // BAJO - MEDIO - ALTO - CRITICO
    private String nivel;

    private LocalDateTime fecha;

    // ACTIVA - RESUELTA
    private String estado;

    // porcentaje que generó la alerta
    private double porcentaje;

    // CONSTRUCTOR VACÍO
    public Alertas() {}

    // GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    // ✅ ESTE ERA EL QUE FALTABA
    public void setId(Long id) {
        this.id = id;
    }

    public Long getMortalidadId() {
        return mortalidadId;
    }

    public void setMortalidadId(Long mortalidadId) {
        this.mortalidadId = mortalidadId;
    }

    public int getJaulaId() {
        return jaulaId;
    }

    public void setJaulaId(int jaulaId) {
        this.jaulaId = jaulaId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}