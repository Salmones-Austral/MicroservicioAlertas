package cl.SalmonesAustral.Alertas.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.SalmonesAustral.Alertas.modelo.Alertas;
import cl.SalmonesAustral.Alertas.service.AlertaService;
import cl.SalmonesAustral.Alertas.dto.CreateAlertasRequest;
import cl.SalmonesAustral.Alertas.mapper.AlertaMapper;

@RestController
@RequestMapping("/api/v1/alertas")
public class AlertasController {

    private final AlertaService service;

    public AlertasController(AlertaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Alertas>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/jaula/{jaulaId}")
    public ResponseEntity<List<Alertas>> getByJaula(@PathVariable int jaulaId) {
        return ResponseEntity.ok(service.getByJaula(jaulaId));
    }

    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<Alertas>> getByNivel(@PathVariable String nivel) {
        return ResponseEntity.ok(service.getByNivel(nivel));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Alertas>> getByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(service.getByEstado(estado));
    }

    // Crear alerta manual (usando DTO)
    @PostMapping
    public ResponseEntity<Alertas> save(@Valid @RequestBody CreateAlertasRequest request) {
        Alertas alerta = AlertaMapper.toModel(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alerta));
    }

    // El endpoint que llamará el microservicio de Mortalidad
    @PostMapping("/generar")
    public ResponseEntity<?> generarAlerta(
            @RequestParam Long mortalidadId,
            @RequestParam int jaulaId,
            @RequestParam double porcentaje) {

        System.out.println("📩 Petición recibida en Alertas para generar alerta (Jaula: " + jaulaId + ", Porcentaje: " + porcentaje + ")");
        Alertas alerta = service.generarAlerta(mortalidadId, jaulaId, porcentaje);

        if (alerta == null) {
            return ResponseEntity.noContent().build(); // Si no superó los límites (ej: < 5%)
        }

        // Simulación de envío a Jorge
        System.out.println("🚀 [SISTEMA DE NOTIFICACIONES] Enviando alerta nivel " + alerta.getNivel() + " a técnico Jorge: " + alerta.getMensaje());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(alerta);
    }
}