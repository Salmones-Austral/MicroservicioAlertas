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

@RestController                                                                  // Le dice a Spring que esta clase es un controlador API REST (devuelve JSONs)
@RequestMapping("/api/v1/alertas")                                               // Define la ruta base en la URL para todos los métodos de esta clase
public class AlertasController {

    private final AlertaService service;                                         // Variable inmutable para guardar el servicio (la lógica de base de datos)

    public AlertasController(AlertaService service) {                            // Constructor: Spring inyecta automáticamente el servicio aquí
        this.service = service;                                                  // Asigna el servicio inyectado a la variable de la clase
    }

    // ==========================================
    // CONSULTAS Y FILTROS
    // ==========================================

    @GetMapping                                                                  // Responde a peticiones GET en "/api/v1/alertas"
    public ResponseEntity<List<Alertas>> getAll() {                              // Promete devolver una lista de todas las alertas
        return ResponseEntity.ok(service.getAll());                              // Va a la BD, trae todo y lo devuelve con un HTTP 200 (OK)
    }

    @GetMapping("/jaula/{jaulaId}")                                              // Responde a peticiones GET en "/jaula/5" (ejemplo)
    public ResponseEntity<List<Alertas>> getByJaula(@PathVariable int jaulaId) { // Extrae el número de jaula de la URL
        return ResponseEntity.ok(service.getByJaula(jaulaId));                   // Filtra las alertas de esa jaula y devuelve HTTP 200
    }

    @GetMapping("/nivel/{nivel}")                                                // Responde a peticiones GET en "/nivel/Critico"
    public ResponseEntity<List<Alertas>> getByNivel(@PathVariable String nivel) {// Extrae el texto del nivel de la URL
        return ResponseEntity.ok(service.getByNivel(nivel));                     // Filtra por gravedad y devuelve la lista con HTTP 200
    }

    @GetMapping("/estado/{estado}")                                              // Responde a peticiones GET en "/estado/Pendiente"
    public ResponseEntity<List<Alertas>> getByEstado(@PathVariable String estado){ // Extrae el estado de la URL
        return ResponseEntity.ok(service.getByEstado(estado));                   // Filtra por estado y devuelve la lista con HTTP 200
    }

    // ==========================================
    // CREACIÓN MANUAL
    // ==========================================

    // Crear alerta manual (usando DTO)
    @PostMapping                                                                 // Responde a peticiones POST para insertar datos nuevos
    public ResponseEntity<Alertas> save(@Valid @RequestBody CreateAlertasRequest request) { // Recibe un JSON, lo valida con reglas DTO y lo mapea
        Alertas alerta = AlertaMapper.toModel(request);                          // Convierte el DTO validad a la entidad real de la base de datos
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alerta)); // Lo guarda en BD y devuelve HTTP 201 (Created)
    }

    // ==========================================
    // MÉTODO DE NEGOCIO (COMUNICACIÓN ASÍNCRONA)
    // ==========================================

    // El endpoint que llamará el microservicio de Mortalidad
    @PostMapping("/generar")                                                     // Responde a peticiones POST en "/generar"
    public ResponseEntity<?> generarAlerta(                                      // Devuelve una respuesta genérica (puede ser la alerta o nada)
            @RequestParam Long mortalidadId,                                     // Recibe por parámetro en la URL el ID de la mortalidad
            @RequestParam int jaulaId,                                           // Recibe por parámetro en la URL el ID de la jaula afectada
            @RequestParam double porcentaje) {                                   // Recibe por parámetro en la URL el % de peces muertos calculado

        // LOG EN CONSOLA: Aviso de que acaba de entrar el evento
        System.out.println("📩 Petición recibida en Alertas para generar alerta (Jaula: " + jaulaId + ", Porcentaje: " + porcentaje + ")"); 
        
        // Ejecuta la lógica de validación en el servicio
        Alertas alerta = service.generarAlerta(mortalidadId, jaulaId, porcentaje); 

        if (alerta == null) {                                                    // Pregunta: ¿El servicio decidió que NO era necesario alertar?
            return ResponseEntity.noContent().build();                           // Si no superó los límites, devuelve HTTP 204 (No Content) y frena aquí
        }

        // Simulación de envío a Jorge (El cruce con Personal/Roles)
        // LOG EN CONSOLA: Muestra la acción que se tomaría en terreno
        System.out.println("🚀 [SISTEMA DE NOTIFICACIONES] Enviando alerta nivel " + alerta.getNivel() + " a técnico Jorge: " + alerta.getMensaje());
        
        // Si todo salió bien y se creó la alerta, la devuelve encapsulada en un HTTP 201
        return ResponseEntity.status(HttpStatus.CREATED).body(alerta);           
    }
}