package com.any.simulados.v1.admin.questoes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;

@DynamoDbBean
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Questao {
    private Long id;

    @JsonProperty
    private String enunciado;

    @JsonProperty
    private List<String> alternativas;

    @JsonProperty
    private String respostaCorreta;

    @JsonProperty
    private Boolean questaoAtiva = true;

    @DynamoDbPartitionKey
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
