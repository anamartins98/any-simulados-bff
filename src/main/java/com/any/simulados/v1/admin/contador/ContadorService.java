package com.any.simulados.v1.admin.contador;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.model.ReturnValue;

import java.util.Map;

@Service
public class ContadorService {

    private final DynamoDbClient dynamoDbClient;
    private final String tableName = "Contadores";

    public ContadorService(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public Long getNextId(String entidade) {
        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("nomeEntidade", AttributeValue.fromS(entidade)))
                .updateExpression("SET ultimoId = if_not_exists(ultimoId, :zero) + :inc")
                .expressionAttributeValues(Map.of(
                        ":zero", AttributeValue.fromN("0"),
                        ":inc", AttributeValue.fromN("1")
                ))
                .returnValues(ReturnValue.UPDATED_NEW)
                .build();

        UpdateItemResponse response = dynamoDbClient.updateItem(request);
        return Long.valueOf(response.attributes().get("ultimoId").n());
    }
}
