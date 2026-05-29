package cl.SalmonesAustral.Alertas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.SalmonesAustral.Alertas.modelo.Alertas;
import cl.SalmonesAustral.Alertas.repository.AlertaRepository;

@Service
public class AlertaService {

    private final AlertaRepository repository;

    public AlertaService(AlertaRepository repository) {
        this.repository = repository;
    }

    // Obtener todas las alertas
    public List<Alertas> getAll() {
        return repository.findAll();
    }

    // Guardar alerta
    public Alertas save(Alertas alertas) {
        return repository.save(alertas);
    }

    // 🔎 NUEVOS MÉTODOS (filtros)
    public List<Alertas> getByJaula(int jaulaId) {
        return repository.findByJaulaId(jaulaId);
    }

    public List<Alertas> getByNivel(String nivel) {
        return repository.findByNivel(nivel);
    }

    public List<Alertas> getByEstado(String estado) {
        return repository.findByEstado(estado);
    }

    // LÓGICA DE NEGOCIO
    public Alertas generarAlerta(Long mortalidadId, int jaulaId, double porcentaje) {

        String nivel;
        String mensaje;

        if (porcentaje > 10) {
            nivel = "CRITICO";
            mensaje = "Alta mortalidad en jaula " + jaulaId;
        } else if (porcentaje >= 5) {
            nivel = "MEDIO";
            mensaje = "Mortalidad moderada en jaula " + jaulaId;
        } else {
            return null; // no se genera alerta
        }

        Alertas alertas = new Alertas();
        alertas.setMortalidadId(mortalidadId);
        alertas.setJaulaId(jaulaId);
        alertas.setPorcentaje(porcentaje);
        alertas.setNivel(nivel);
        alertas.setMensaje(mensaje);
        alertas.setEstado("ACTIVA");
        alertas.setFecha(LocalDateTime.now());

        return repository.save(alertas);
    }
}
