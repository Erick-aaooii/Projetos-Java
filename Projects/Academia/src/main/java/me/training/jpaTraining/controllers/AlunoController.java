package me.training.jpaTraining.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import me.training.jpaTraining.forms.AlunoDto;
import me.training.jpaTraining.models.Aluno;
import me.training.jpaTraining.service.AlunoService;

@RestController
@RequestMapping("/alunos")  // Define a URL base para este controller como "/alunos"
public class AlunoController {

    @Autowired  // A injeção automática do serviço AlunoService para ser usado pelos métodos do controller
    private AlunoService service;

    /**
     * Endpoint para criar um novo aluno.
     * 
     * @param form - Dados do aluno no formato DTO (AlunoDto) enviados no corpo da requisição.
     * @return Retorna uma resposta com status 202 (Accepted) e mensagem de sucesso.
     */
    @PostMapping
    public ResponseEntity<String> alunosCreate(
        @Valid @ModelAttribute AlunoDto form
        ) {
        service.create(form);  // Chama o serviço para criar o aluno.
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("usuário criado");  // Retorna a resposta de sucesso.
    }

    /**
     * Endpoint para buscar um aluno pelo ID.
     * 
     * @param id - O ID do aluno a ser recuperado.
     * @return Retorna os dados do aluno com status 202 (Accepted).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> alunosGet(@PathVariable @NotNull @Positive Long id) {
        // Chama o serviço para buscar o aluno pelo ID e retorna a resposta com os dados do aluno.
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.get(id));  
    }
    
    /**
     * Endpoint para atualizar os dados de um aluno existente.
     * 
     * @param id - O ID do aluno a ser atualizado.
     * @param form - Os dados atualizados do aluno no formato DTO (AlunoDto).
     * @return Retorna os dados atualizados do aluno com status 202 (Accepted).
     */
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> alunosUpdate(@PathVariable Long id, @RequestBody AlunoDto form) {
        // Chama o serviço para atualizar o aluno e retorna os dados atualizados.
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, form));  
    }

    /**
     * Endpoint para deletar um aluno.
     * 
     * @param id - O ID do aluno a ser deletado.
     * @return Retorna um mapa com a mensagem de sucesso da exclusão com status 202 (Accepted).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> alunosDelete(@PathVariable @NotNull @Positive Long id) {
        // Chama o serviço para deletar o aluno e retorna uma resposta com status 202.
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.delete(id));  
    }

    /**
     * Endpoint para listar todos os alunos.
     * 
     * @return Retorna uma lista de alunos com status 200 (OK).
     */
    @GetMapping("/list")
    public ResponseEntity<List<Aluno>> alunosList() {
        // Chama o serviço para listar todos os alunos e retorna a lista.
        return ResponseEntity.status(HttpStatus.OK).body(service.list());  
    }
}