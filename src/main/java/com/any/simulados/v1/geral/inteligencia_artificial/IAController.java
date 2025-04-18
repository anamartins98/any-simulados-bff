package com.any.simulados.v1.geral.inteligencia_artificial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/ia")
public class IAController {
    @Autowired
    private IAService iaService;

    @PostMapping
    public ResponseEntity<String> rodarPrompt(@RequestBody String prompt) {
        return iaService.rodarPrompt(prompt);

    }
}
