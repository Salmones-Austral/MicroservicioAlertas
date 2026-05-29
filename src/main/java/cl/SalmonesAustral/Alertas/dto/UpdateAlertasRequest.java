package cl.SalmonesAustral.Alertas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * DTO para actualizar un libro existente (PUT) No incluye ID porque se obtiene del path parameter
 */
public record UpdateAlertasRequest(
                @NotBlank(message = "Nivel no puede ser vacío") String nivel,

                @NotBlank(message = "Mensaje no puede ser vacío") String mensaje,

                @NotBlank(message = "Estado no puede ser vacío") String estado) {   
                }
    
  
