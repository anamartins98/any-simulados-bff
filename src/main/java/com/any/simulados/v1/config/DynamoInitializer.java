package com.any.simulados.v1.config;

import com.any.simulados.v1.admin.questoes.Questao;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.services.dynamodb.*;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.Map;

@Component
public class DynamoInitializer {

    private final DynamoDbEnhancedClient enhancedClient;
    private final DynamoDbClient dynamoDbClient;

    public DynamoInitializer(DynamoDbEnhancedClient enhancedClient, DynamoDbClient dynamoDbClient) {
        this.enhancedClient = enhancedClient;
        this.dynamoDbClient = dynamoDbClient;
    }

    @PostConstruct
    public void init() {
        criarTabelaQuestoes();
        criarTabelaContadores();
        inserirContadorInicial("questao");
    }

    private void criarTabelaQuestoes() {
        DynamoDbTable<Questao> questaoTable = enhancedClient.table("Questoes", TableSchema.fromBean(Questao.class));
        try {
            questaoTable.createTable();
            System.out.println("Tabela 'Questoes' criada.");
        } catch (ResourceInUseException e) {
            System.out.println("Tabela 'Questoes' já existe.");
        }
    }

    private void criarTabelaContadores() {
        String tableName = "Contadores";

        boolean existe = dynamoDbClient.listTables().tableNames().contains(tableName);
        if (existe) {
            System.out.println("Tabela 'Contadores' já existe.");
            return;
        }

        CreateTableRequest request = CreateTableRequest.builder()
                .tableName(tableName)
                .keySchema(KeySchemaElement.builder()
                        .attributeName("nomeEntidade")
                        .keyType(KeyType.HASH)
                        .build())
                .attributeDefinitions(AttributeDefinition.builder()
                        .attributeName("nomeEntidade")
                        .attributeType(ScalarAttributeType.S)
                        .build())
                .provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(1L)
                        .writeCapacityUnits(1L)
                        .build())
                .build();

        dynamoDbClient.createTable(request);
        System.out.println("Tabela 'Contadores' criada com sucesso.");
    }

    private void inserirContadorInicial(String entidade) {
        try {
            PutItemRequest putRequest = PutItemRequest.builder()
                    .tableName("Contadores")
                    .item(Map.of(
                            "nomeEntidade", AttributeValue.fromS(entidade),
                            "ultimoId", AttributeValue.fromN("0")
                    ))
                    .conditionExpression("attribute_not_exists(nomeEntidade)")
                    .build();

            dynamoDbClient.putItem(putRequest);
            System.out.println("Contador inicial inserido para '" + entidade + "'.");
        } catch (ConditionalCheckFailedException e) {
            System.out.println("Contador para '" + entidade + "' já existe.");
        }
    }
}
