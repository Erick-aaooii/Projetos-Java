package me.training.jpaTraining.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import me.training.jpaTraining.forms.MatriculaDto;
import me.training.jpaTraining.models.Aluno;
import me.training.jpaTraining.models.Matricula;
import me.training.jpaTraining.models.TipoDeTreino;
import me.training.jpaTraining.repository.MatriculaRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository repository;

    @Autowired
    private AlunoService alunoService;

    // Método para criar a matrícula
    public Matricula create(MatriculaDto form) {

        // Buscando o aluno pelo ID
        Aluno aluno = alunoService.get(form.alunoId());

        // Verifica se o aluno existe
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não encontrado com o ID: " + form.alunoId());
        }

        Matricula matricula = new Matricula();
        // Converte o nome do treino (String) para o valor da enum TipoDeTreino
        TipoDeTreino tipoDeTreino = TipoDeTreino.fromNome(form.treino());

        // Preenche a matrícula com os dados recebidos
        matricula.setAluno(aluno);
        matricula.setTreino(tipoDeTreino);  // Aqui está a conversão de String para o tipo enum
        matricula.setValor(form.valor());

        return repository.save(matricula);
    }

    // Método para buscar matrícula por ID
    public Matricula get(Long id) {
        Optional<Matricula> matricula = repository.findById(id);
        if (matricula.isPresent()) {
            return matricula.get();
        } else {
            throw new IllegalArgumentException("Matrícula não encontrada com o ID: " + id);
        }
    }

    // Método para atualizar matrícula
    public Matricula update(Long id, MatriculaDto form) {
        // Verifica se a matrícula existe
        Matricula matricula = get(id);

        // Atualiza os campos da matrícula com as informações do DTO
        Aluno aluno = alunoService.get(form.alunoId());
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não encontrado com o ID: " + form.alunoId());
        }

        // Converte o nome do treino (String) para o valor da enum TipoDeTreino
        TipoDeTreino tipoDeTreino = TipoDeTreino.fromNome(form.treino());

        // Preenche a matrícula com os dados recebidos
        matricula.setAluno(aluno);
        matricula.setTreino(tipoDeTreino);  // Aqui está a conversão de String para o tipo enum
        matricula.setValor(form.valor());

        return repository.save(matricula);
    }

    // Método para excluir matrícula
    public Map<String, Object> delete(Long id) {
        // Verifica se a matrícula existe
        Matricula matricula = get(id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Mátricula deletada");
        response.put("status", HttpStatus.OK.value());
        // Exclui a matrícula
        repository.delete(matricula);
        return response;
    }

    // Método para listar todas as matrículas
    public Iterable<Matricula> getAll() {
        return repository.findAll();
    }
}

