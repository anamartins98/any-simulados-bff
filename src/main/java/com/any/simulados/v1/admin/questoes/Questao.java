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

    @DynamoDbPartitionKey
    public Long getId() {
        return id;
    }
}
