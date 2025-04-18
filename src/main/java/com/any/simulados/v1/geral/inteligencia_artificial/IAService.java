package com.any.simulados.v1.geral.inteligencia_artificial;

import com.jayway.jsonpath.JsonPath;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Service
public class IAService {
    private final WebClient webClient;
    private final String apiKey = "AIzaSyAwr6dQ4LvMVmCRIwE1J726G_Qw2u4u4Jw";

    public IAService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash")
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    public ResponseEntity<String> rodarPrompt(String prompt) {
        BodyInput body = new BodyInput(prompt);

        ResponseEntity<String> response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(":generateContent")
                        .queryParam("key", apiKey)
                        .build())
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .bodyValue(body)
                .retrieve()
                .toEntity(String.class)
                .block();

        if (response != null && response.getBody() != null) {
            try {
                // Usando JsonPath para extrair o valor de "text"
                String text = JsonPath.read(response.getBody(), "$.candidates[0].content.parts[0].text");
                return ResponseEntity.ok().body(new String(text));
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body(new String("Ocorreu um erro."));
            }
        }

        return ResponseEntity.internalServerError().body(new String("Ocorreu um erro."));
    }
}
