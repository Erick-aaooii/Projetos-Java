package me.training.jpaTraining.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Representa um aluno no sistema.
 * Esta classe é uma entidade JPA mapeada para a tabela "tb_alunos" no banco de dados.
 * Contém informações pessoais do aluno, uma lista de avaliações físicas e matrículas associadas ao aluno.
 */
@Data
@Entity
@Table(name = "tb_alunos")  // Define o nome da tabela no banco de dados
@JsonIgnoreProperties({"hibernateLazyInitializer", "Handler"})  // Ignora propriedades internas do Hibernate na serialização JSON
public class Aluno {

    /**
     * Identificador único do aluno.
     * O valor é gerado automaticamente pelo banco de dados (auto incremento).
     */
    @Id  // Define o campo como chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Define que o ID será gerado automaticamente pelo banco
    private Long id;

    /**
     * Nome do aluno.
     * O nome é uma informação básica do aluno, não possui restrições específicas no banco.
     */
    private String nome;

    /**
     * CPF do aluno.
     * O CPF deve ser único e não pode ser nulo.
     * O valor será indexado para garantir unicidade.
     */
    @Column(unique = true, nullable = false)  // Garante que o CPF seja único e não nulo no banco de dados
    private String cpf;

    /**
     * Bairro onde o aluno reside.
     * Pode ser nulo ou vazio.
     */
    private String bairro;

    /**
     * Idade do aluno.
     * Pode ser nulo.
     */
    private Integer idade;

    /**
     * Data de nascimento do aluno.
     * Pode ser nula, mas geralmente é utilizada para calcular a idade do aluno.
     */
    private LocalDate dataDeNascimento;

    /**
     * Lista de avaliações físicas associadas ao aluno.
     * Uma avaliação física pode ter um único aluno associado.
     * A relação é de um para muitos, onde um aluno pode ter várias avaliações físicas.
     */
    @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY)  // Relacionamento um-para-muitos com a entidade AvaliacaoFisica
    @JsonIgnore  // Ignora o campo "avaliacoes" ao serializar para JSON
    private List<AvaliacaoFisica> avaliacoes = new ArrayList<>();

    /**
     * Lista de matrículas associadas ao aluno.
     * Um aluno pode ter várias matrículas, uma para cada tipo de treino.
     */
    @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore  // Ignora o campo "matriculas" ao serializar para JSON
    private List<Matricula> matriculas = new ArrayList<>();

    /**
     * Foto do aluno.
     * Pode ser uma URL ou um caminho de arquivo. 
     * Essa imagem pode ser exibida no sistema para representar o aluno.
     */
    private List<String> img;
}
