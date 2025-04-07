package com.any.simulados.v1.admin.questoes;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class QuestaoRepository {

    private final DynamoDbTable<Questao> questaoTable;

    public QuestaoRepository(DynamoDbEnhancedClient enhancedClient) {
        this.questaoTable = enhancedClient.table("Questoes", TableSchema.fromBean(Questao.class));
    }

    public void save(Questao questao) {
        questaoTable.putItem(questao);
    }

    public Questao findById(Long id) {
        return questaoTable.getItem(r -> r.key(k -> k.partitionValue(id)));
    }

    public List<Questao> findAll() {
        return StreamSupport.stream(questaoTable.scan().items().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        questaoTable.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }
}
