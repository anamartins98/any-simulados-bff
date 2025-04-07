package com.any.simulados.v1.exceptions;

public class GenericException {
    private int statusCode;
    private String mensagem;

    public GenericException(int statusCode, String mensagem) {
        this.statusCode  = statusCode;
        this.mensagem = mensagem;
    }
}
