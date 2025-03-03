package me.training.jpaTraining.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Representa uma matrícula de um aluno no sistema.
 * Esta classe é uma entidade JPA mapeada para a tabela "matriculas" no banco de dados.
 * Cada matrícula está associada a um aluno, contém a data da matrícula, o tipo de treino escolhido e o valor da matrícula.
 */
@Data
@Entity  // Define que a classe é uma entidade JPA, mapeada para uma tabela no banco de dados
@Table(name = "matriculas")  // Define o nome da tabela no banco de dados
public class Matricula {

    /**
     * Identificador único da matrícula.
     * O valor será gerado automaticamente pelo banco de dados (auto incremento).
     */
    @Id  // Define o campo como chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Define que o ID será gerado automaticamente pelo banco
    private Long id;

    /**
     * Relacionamento muitos para um (Many-to-One) entre a matrícula e o aluno.
     * A matrícula está associada a um único aluno, mas um aluno pode ter várias matrículas.
     */
    @ManyToOne(fetch = FetchType.LAZY)  // Define um relacionamento muitos para um entre Matricula e Aluno
    @JoinColumn(name = "aluno_id", nullable = false)  // Define o nome da coluna que será a chave estrangeira (aluno_id) e que é obrigatória
    private Aluno aluno;

    /**
     * Data e hora da matrícula.
     * A data da matrícula será atribuída automaticamente quando a matrícula for criada, por meio do método `onCreate`.
     */
    private LocalDateTime dataDaMatricula;

    /**
     * Tipo de treino escolhido para a matrícula.
     * Este campo armazena a opção de treino selecionada pelo aluno na matrícula.
     */
    @Enumerated(EnumType.STRING)  // Mapeia o enum TipoDeTreino como uma string na base de dados
    @NotNull  // Garante que o tipo de treino não pode ser nulo
    private TipoDeTreino treino;

    /**
     * Valor da matrícula.
     * Este campo armazena o valor que o aluno pagará pela matrícula.
     * O valor será atribuído no momento da criação da matrícula.
     */
    @NotNull  // Garante que o valor não pode ser nulo
    private BigDecimal valor;  // Tipo BigDecimal para armazenar valores monetários com precisão

    /**
     * Método que é chamado automaticamente antes de a matrícula ser persistida no banco de dados.
     * Ele define o campo `dataDaMatricula` como a data e hora atuais no momento da criação da matrícula.
     */
    @PrePersist  // A anotação indica que o método será chamado antes da inserção no banco
    protected void onCreate() {
        this.dataDaMatricula = LocalDateTime.now();  // Atribui o valor da data e hora atual
    }
}