package cl.SalmonesAustral.Alertas.mapper;

import cl.SalmonesAustral.Alertas.dto.CreateAlertasRequest;
import cl.SalmonesAustral.Alertas.dto.UpdateAlertasRequest;
import cl.SalmonesAustral.Alertas.modelo.Alertas;

import java.time.LocalDateTime;

/**
 * Mapper para convertir DTOs al modelo de dominio (Alertas)
 */
public class AlertaMapper {

    // Convierte el DTO de creación al Modelo de la BD
    public static Alertas toModel(CreateAlertasRequest request) {
        Alertas alerta = new Alertas();

        alerta.setId(null); // La Base de Datos autogenera el ID
        alerta.setMortalidadId(request.mortalidadId());
        alerta.setJaulaId(request.jaulaId());
        alerta.setPorcentaje(request.porcentaje());
        alerta.setNivel(request.nivel());
        alerta.setMensaje(request.mensaje());
        alerta.setEstado("ACTIVA"); // Toda alerta nace activa
        alerta.setFecha(LocalDateTime.now()); // Hora actual de la alerta

        return alerta;
    }

    // Convierte el DTO de actualización al Modelo de la BD
    public static Alertas toModel(Long id, UpdateAlertasRequest request) {
        Alertas alerta = new Alertas();

        alerta.setId(id); // Mantenemos el ID original
        alerta.setNivel(request.nivel());
        alerta.setMensaje(request.mensaje());
        alerta.setEstado(request.estado());
        // La fecha, el jaulaId y mortalidadId no se modifican en un PUT común

        return alerta;
    }
}
