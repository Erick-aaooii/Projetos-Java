package me.training.jpaTraining.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import me.training.jpaTraining.forms.AlunoDto;
import me.training.jpaTraining.models.Aluno;
import me.training.jpaTraining.repository.AlunoRepository;

/**
 * Serviço de gerenciamento dos alunos. Contém métodos para criar, 
 * listar, atualizar e deletar alunos no banco de dados.
 * A classe usa a camada de persistência do Spring Data JPA através do repositório `AlunoRepository`.
 */
@Service  // Anotação que indica que a classe é um serviço, ou seja, uma camada de lógica de negócios
public class AlunoService {

    /**
     * Repositório para acesso aos dados dos alunos no banco de dados.
     * O repositório é injetado automaticamente pelo Spring.
     */
    @Autowired 
    private AlunoRepository repository;

    @Autowired
    private ImgService service;

    /**
     * Cria um novo aluno a partir de um objeto DTO e uma imagem.
     * O aluno é salvo no banco de dados, e a imagem é salva no diretório especificado.
     * 
     * @param form Dados do aluno que serão utilizados para criar o novo aluno.
     * @param file A imagem do aluno a ser carregada.
     * @return O aluno criado, já persistido no banco.
     */
    public Aluno create(AlunoDto form) {

        Aluno alunoNovo = new Aluno();  // Cria um novo objeto Aluno
        List<String> urls = service.saveImgs(form);
        // Preenche os dados do aluno com os valores do DTO
        alunoNovo.setNome(form.nome());
        alunoNovo.setCpf(form.cpf());
        alunoNovo.setBairro(form.bairro());
        alunoNovo.setIdade(form.idade());
        alunoNovo.setDataDeNascimento(form.dataDeNascimento());
        alunoNovo.setImg(urls);  // Define a URL da imagem no campo "img"

        // Salva o aluno no banco de dados e retorna o aluno salvo
        return repository.save(alunoNovo);
    }

    /**
     * Recupera um aluno do banco de dados baseado no ID fornecido.
     * Se o aluno não for encontrado, lança uma exceção.
     * 
     * @param id ID do aluno que será recuperado.
     * @return O aluno correspondente ao ID fornecido.
     * @throws RuntimeException Caso o aluno não seja encontrado.
     */
    public Aluno get(Long id) {
        // Procura o aluno no banco de dados. Se não encontrado, lança exceção.
        return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Nenhum aluno encontrado com id: " + id));
    }

    /**
     * Recupera todos os alunos do banco de dados.
     * 
     * @return Uma lista de todos os alunos.
     */
    public List<Aluno> list() {
        // Retorna todos os alunos registrados no banco de dados.
        return repository.findAll();
    }

    /**
     * Atualiza um aluno existente com novos dados fornecidos.
     * O método verifica se os campos CPF e data de nascimento são alterados, e se forem, lança uma exceção.
     * 
     * @param id ID do aluno a ser atualizado.
     * @param form Dados atualizados para o aluno.
     * @return O aluno atualizado.
     * @throws ResponseStatusException Caso o CPF ou a data de nascimento sejam alterados.
     */
    public Aluno update(Long id, AlunoDto form) {
        // Procura o aluno no banco de dados
        Aluno alunoExistente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Nenhum aluno encontrado com id: " + id));

        // Verifica se o CPF ou a data de nascimento foram alterados
        if (!alunoExistente.getCpf().equals(form.cpf())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Cpf Não pode ser Alterado");
        }
        if (!alunoExistente.getDataDeNascimento().equals(form.dataDeNascimento())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data de Nascimento Não pode ser Alterada");
        }

        List<String> urls = service.saveImgs(form);

        // Atualiza os campos do aluno com os dados do DTO
        alunoExistente.setNome(form.nome());
        alunoExistente.setCpf(form.cpf());
        alunoExistente.setBairro(form.bairro());
        alunoExistente.setIdade(form.idade());
        alunoExistente.setDataDeNascimento(form.dataDeNascimento());
        alunoExistente.setImg(urls);

        // Salva o aluno atualizado no banco de dados
        return repository.save(alunoExistente);
    }

    /**
     * Deleta um aluno do banco de dados com base no ID.
     * Se o aluno não for encontrado, lança uma exceção.
     * 
     * @param id ID do aluno a ser deletado.
     * @return Uma mensagem de confirmação da exclusão.
     * @throws RuntimeException Caso o aluno não seja encontrado.
     */
    public Map<String, Object> delete(Long id) {
        // Procura o aluno no banco de dados
        Aluno alunoExistente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Nenhum aluno encontrado com id: " + id));
        
        // Deleta o aluno do banco de dados
        repository.delete(alunoExistente);
        
        // Cria um mapa para retornar uma resposta com o status e mensagem de sucesso
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Usuário deletado");
        response.put("status", HttpStatus.OK.value());

        // Retorna o mapa de resposta
        return response;
    }
}
