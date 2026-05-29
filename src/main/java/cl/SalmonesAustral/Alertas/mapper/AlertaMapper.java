package cl.SalmonesAustral.Alertas.mapper;


import cl.SalmonesAustral.Alertas.dto.CreateAlertasRequest;
import cl.SalmonesAustral.Alertas.dto.UpdateAlertasRequest;
import cl.SalmonesAustral.Alertas.modelo.Alertas;

import java.time.LocalDateTime;

/**
 * Mapper para convertir DTOs a modelo de dominio (Alertas)
 */
public class AlertaMapper {

    // 🔹 CREATE (POST)
    public static Alertas toModel(CreateAlertasRequest request) {
        Alertas alerta = new Alertas();

        alerta.setId(null); // se genera en DB
        alerta.setMortalidadId(request.mortalidadId());
        alerta.setJaulaId(request.jaulaId());
        alerta.setPorcentaje(request.porcentaje());
        alerta.setNivel(request.nivel());
        alerta.setMensaje(request.mensaje());
        alerta.setEstado("ACTIVA");
        alerta.setFecha(LocalDateTime.now());

        return alerta;
    }

    // 🔹 UPDATE (PUT)
    public static Alertas toModel(Long id, UpdateAlertasRequest request) {
        Alertas alerta = new Alertas();

        alerta.setId(id);
        alerta.setNivel(request.nivel());
        alerta.setMensaje(request.mensaje());
        alerta.setEstado(request.estado());

        return alerta;
    }
}
