package cl.SalmonesAustral.Alertas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.SalmonesAustral.Alertas.modelo.Alertas;
import cl.SalmonesAustral.Alertas.service.AlertaService;

@RestController
@RequestMapping("/alertas")
public class AlertasController {

    private final AlertaService service;

    public AlertasController(AlertaService service) {
        this.service = service;
    }

    //Obtener todas las alertas
    @GetMapping
    public ResponseEntity<List<Alertas>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    //Buscar por jaula
    @GetMapping("/jaula/{jaulaId}")
    public ResponseEntity<List<Alertas>> getByJaula(@PathVariable int jaulaId) {
        return ResponseEntity.ok(service.getByJaula(jaulaId));
    }

    //Buscar por nivel (CRITICO, MEDIO)
    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<Alertas>> getByNivel(@PathVariable String nivel) {
        return ResponseEntity.ok(service.getByNivel(nivel));
    }

    // Buscar por estado (ACTIVA, RESUELTA)
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Alertas>> getByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(service.getByEstado(estado));
    }

    //Crear alerta manual (opcional)
    @PostMapping
    public ResponseEntity<Alertas> save(@RequestBody Alertas alerta) {
        return ResponseEntity.ok(service.save(alerta));
    }

    //Generar alerta automáticamente desde mortalidad
    @PostMapping("/generar")
    public ResponseEntity<?> generarAlerta(
            @RequestParam Long mortalidadId,
            @RequestParam int jaulaId,
            @RequestParam double porcentaje) {

        Alertas alerta = service.generarAlerta(mortalidadId, jaulaId, porcentaje);

        if (alerta == null) {
            return ResponseEntity.noContent().build(); // no cumple condiciones
        }

        return ResponseEntity.ok(alerta);
    }
}
