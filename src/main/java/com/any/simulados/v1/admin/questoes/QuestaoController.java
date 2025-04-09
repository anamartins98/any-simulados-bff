package com.any.simulados.v1.admin.questoes;

import com.any.simulados.v1.admin.contador.ContadorService;
import com.any.simulados.v1.exceptions.GenericException;
import com.any.simulados.v1.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/questoes")
public class QuestaoController {
    @Autowired
    private ContadorService contadorService;
    @Autowired
    private QuestaoRepository questaoRepository;

    @PostMapping
    public ResponseEntity<Questao> cadastrarQuestao(@RequestBody Questao entrada) {
        Logger.postar("Iniciando método cadastrarQuestao...");
        Long novoId = contadorService.getNextId("Questoes");
        entrada.setId(novoId);
        questaoRepository.save(entrada);

        URI location = URI.create("/v1/questoes/" + novoId);
        return ResponseEntity.created(location).body(entrada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Questao> obterQuestao(@PathVariable("id") Long id) {
        try {
            Questao questao = questaoRepository.findById(id);
            if (questao != null)
                return ResponseEntity.ok(questao);
        } catch(Exception e) {
            System.out.println("Ocorreu um erro ao tentar obter questão: " + e.getMessage());
            ResponseEntity.internalServerError().body(new GenericException(500, e.getMessage()));
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Questao>> obterQuestoes() {
        try {
            List<Questao> questoes = questaoRepository.findAll();
            if (!questoes.isEmpty())
                return ResponseEntity.ok(questoes);
        } catch(Exception e) {
            System.out.println("Ocorreu um erro ao tentar obter questões: " + e.getMessage());
            ResponseEntity.internalServerError().body(new GenericException(500, e.getMessage()));
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Questao> deletarQuestao(@PathVariable("id") Long id) {
        try {
            Questao questao = questaoRepository.findById(id);
            if (questao != null) {
                questaoRepository.delete(id);
                return ResponseEntity.accepted().body(questao);
            }
        } catch(Exception e) {
            System.out.println("Ocorreu um erro ao tentar deletar questão: " + e.getMessage());
            ResponseEntity.internalServerError().body(new GenericException(500, e.getMessage()));
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarQuestao(@PathVariable("id") Long id, @RequestBody Questao entrada) {
        if(id == entrada.getId()) {
            questaoRepository.update(entrada);
            return ResponseEntity.accepted().body(entrada);
        }
        return ResponseEntity.internalServerError().body(new GenericException(500, "ID de recurso divergente do ID de payload."));
    }
}
