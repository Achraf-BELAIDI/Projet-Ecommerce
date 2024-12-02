package com.alten.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class OpenApiExporter {

    @Value("${openapi.export.path:target/openapi.json}")
    private String exportPath;

    @EventListener(ApplicationReadyEvent.class)
    public void exportOpenApi() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String openApiUrl = "http://localhost:8080/v3/api-docs"; // Assurez-vous que l'URL est correcte pour votre serveur
            String openApiJson = restTemplate.getForObject(openApiUrl, String.class);

            if (openApiJson != null) {
                File file = new File(exportPath);
                file.getParentFile().mkdirs(); // Crée les répertoires parents s'ils n'existent pas
                try (FileWriter fileWriter = new FileWriter(file)) {
                    fileWriter.write(openApiJson);
                    System.out.println("OpenAPI JSON exporté vers : " + file.getAbsolutePath());
                }
            } else {
                System.err.println("Erreur lors de la récupération de la documentation OpenAPI.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
