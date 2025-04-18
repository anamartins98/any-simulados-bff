package com.any.simulados.v1.geral.inteligencia_artificial;


import lombok.Data;

@Data
public class IAResponse {
    private String text;
    private int statusCode;
    private String message;

    public IAResponse(String text, int statusCode, String message) {
        this.text = text;
        this.statusCode = statusCode;
        this.message = message;
    }
}
