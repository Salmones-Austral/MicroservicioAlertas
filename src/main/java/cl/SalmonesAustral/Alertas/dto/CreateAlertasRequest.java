package cl.SalmonesAustral.Alertas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * DTO para crear una nueva Alerta (POST)
 */
public record CreateAlertasRequest(
        @NotNull(message = "El ID de mortalidad no puede ser nulo")
        @PositiveOrZero(message = "ID de mortalidad no puede ser negativo") 
        Long mortalidadId,
        
        @PositiveOrZero(message = "ID de jaula no puede ser negativo") 
        int jaulaId,
        
        @NotBlank(message = "El mensaje no puede estar vacío") 
        String mensaje,
        
        @NotBlank(message = "El nivel (CRITICO, MEDIO, BAJO) no puede estar vacío") 
        String nivel,
        
        @PositiveOrZero(message = "El porcentaje no puede ser negativo") 
        double porcentaje
) {}
