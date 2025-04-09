package com.any.simulados.v1.admin.questoes;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;

@DynamoDbBean
@Data
public class Questao {
    private Long id;
    private String enunciado;
    private List<String> alternativas;
    private String respostaCorreta;
    private Boolean questaoAtiva = true;

    @DynamoDbPartitionKey
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
