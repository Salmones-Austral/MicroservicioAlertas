package cl.SalmonesAustral.Alertas.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO para actualizar una Alerta existente (PUT)
 */
public record UpdateAlertasRequest(
        @NotBlank(message = "El nivel no puede estar vacío") 
        String nivel,

        @NotBlank(message = "El mensaje no puede estar vacío") 
        String mensaje,

        @NotBlank(message = "El estado (ACTIVA, RESUELTA) no puede estar vacío") 
        String estado
) {}
