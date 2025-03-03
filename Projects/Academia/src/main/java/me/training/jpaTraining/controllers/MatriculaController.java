package me.training.jpaTraining.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import me.training.jpaTraining.forms.MatriculaDto;
import me.training.jpaTraining.models.Matricula;
import me.training.jpaTraining.service.MatriculaService;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService service;

    // Método para criar a matrícula
    @PostMapping
    public ResponseEntity<Matricula> create(@Valid @RequestBody MatriculaDto form) {
        Matricula matricula = service.create(form);
        return ResponseEntity.status(201).body(matricula);  // Retorna a matrícula criada com status 201 (Created)
    }

    // Método para buscar a matrícula pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Matricula> getById(@PathVariable Long id) {
        Matricula matricula = service.get(id);
        return ResponseEntity.ok(matricula);  // Retorna a matrícula com status 200 (OK)
    }

    // Método para atualizar a matrícula
    @PutMapping("/{id}")
    public ResponseEntity<Matricula> update(@PathVariable Long id, @Valid @RequestBody MatriculaDto form) {
        Matricula matricula = service.update(id, form);
        return ResponseEntity.ok(matricula);  // Retorna a matrícula atualizada com status 200 (OK)
    }

    // Método para excluir a matrícula
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));  // Retorna status 204 (No Content) após exclusão
    }

    // Método para listar todas as matrículas
    @GetMapping("/list")
    public ResponseEntity<Iterable<Matricula>> getAll() {
        Iterable<Matricula> matriculas = service.getAll();
        return ResponseEntity.ok(matriculas);  // Retorna todas as matrículas com status 200 (OK)
    }
}
