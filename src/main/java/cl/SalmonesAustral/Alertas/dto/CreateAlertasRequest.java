package cl.SalmonesAustral.Alertas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * DTO para crear un nuevo libro (POST) No incluye ID porque se genera automáticamente
 */
public record CreateAlertasRequest(
                @PositiveOrZero(message = "ID de mortalidad no puede ser negativo") Long mortalidadId,
                @PositiveOrZero(message = "ID de jaula no puede ser negativo") int jaulaId,
                @NotBlank(message = "Mensaje no puede ser vacío") String mensaje,
                @NotBlank(message = "Nivel no puede ser vacío") String nivel,
                @PositiveOrZero(message = "Porcentaje no puede ser negativo") double porcentaje) {
}

