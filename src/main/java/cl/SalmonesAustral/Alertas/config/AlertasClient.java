package cl.SalmonesAustral.Alertas.config;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AlertasClient {

    private final WebClient webClient;

    public AlertasClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public void enviarAlerta(Long mortalidadId, int jaulaId, double porcentaje) {

        webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/alertas/generar")
                        .queryParam("mortalidadId", mortalidadId)
                        .queryParam("jaulaId", jaulaId)
                        .queryParam("porcentaje", porcentaje)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(); // ejecuta la llamada
    }
}
