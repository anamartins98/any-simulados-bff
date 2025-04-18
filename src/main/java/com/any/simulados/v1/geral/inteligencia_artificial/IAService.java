package com.any.simulados.v1.geral.inteligencia_artificial;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(":generateContent")
                        .queryParam("key", apiKey)
                        .build())
                .bodyValue(body)
                .retrieve()
                .toEntity(String.class)
                .block();
    }
}
