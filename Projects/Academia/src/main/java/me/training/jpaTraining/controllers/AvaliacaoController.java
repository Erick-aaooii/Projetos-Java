package me.training.jpaTraining.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import me.training.jpaTraining.forms.AvaliacaoDto;
import me.training.jpaTraining.models.AvaliacaoFisica;
import me.training.jpaTraining.service.AvaliacaoService;

@RestController
@RequestMapping("/avaliacao")  // Define a URL base para este controller como "/avaliacao"
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService service;

    /**
     * Endpoint para criar uma nova avaliação física.
     * 
     * @param form - Dados da avaliação no formato DTO (AvaliacaoDto) enviados no corpo da requisição.
     * @return Retorna uma resposta com status 202 (Accepted) e mensagem de sucesso.
     */
    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody AvaliacaoDto form) {
        service.create(form);  // Chama o serviço para criar a avaliação física.
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Avaliação criada");  // Retorna a resposta de sucesso.
    }

    /**
     * Endpoint para buscar uma avaliação física pelo ID.
     * 
     * @param id - O ID da avaliação a ser recuperada.
     * @return Retorna os dados da avaliação física com status 202 (Accepted).
     */
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoFisica> get(@PathVariable @NotNull @Positive long id) {
        // Chama o serviço para buscar a avaliação física pelo ID e retorna a resposta com os dados da avaliação.
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.get(id));  
    }
    
    /**
     * Endpoint para listar todas as avaliações físicas.
     * 
     * @return Retorna uma lista de avaliações com status 200 (OK).
     */
    @GetMapping("/list")
    public ResponseEntity<List<AvaliacaoFisica>> list() {
        // Chama o serviço para listar todas as avaliações físicas e retorna a lista.
        return ResponseEntity.status(HttpStatus.OK).body(service.list());  
    }
}
