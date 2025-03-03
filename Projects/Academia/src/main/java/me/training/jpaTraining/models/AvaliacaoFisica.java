package me.training.jpaTraining.models;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Representa uma avaliação física de um aluno no sistema. 
 * Esta classe é uma entidade JPA mapeada para a tabela "tb_avaliacoes" no banco de dados.
 * Cada avaliação física contém informações como peso, altura, e a data da avaliação.
 */
@Data
@Entity
@Table(name = "tb_avaliacoes")  // Define o nome da tabela no banco de dados
public class AvaliacaoFisica {

    /**
     * Identificador único da avaliação física.
     * O valor é gerado automaticamente pelo banco de dados (auto incremento).
     */
    @Id  // Define o campo como chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Define que o ID será gerado automaticamente pelo banco
    private Long id;

    /**
     * Referência ao aluno associado à avaliação física.
     * Este campo é uma chave estrangeira que faz referência ao aluno que está sendo avaliado.
     * O campo é obrigatório e a operação de cascata é ativada, permitindo salvar a avaliação junto com o aluno.
     */
    @ManyToOne(cascade = CascadeType.ALL)  // Relacionamento "muitos para um", ou seja, várias avaliações podem ser associadas a um único aluno
    @JoinColumn(name = "aluno_id", nullable = false)  // Define o nome da coluna que será a chave estrangeira
    private Aluno aluno;

    /**
     * Data e hora da avaliação física.
     * A data da avaliação será atribuída automaticamente quando a avaliação for criada, por meio do método `onCreate`.
     */
    private LocalDateTime dataDaAvaliacao;

    /**
     * Peso do aluno no momento da avaliação física.
     * Este valor é armazenado como um número de ponto flutuante.
     */
    private double peso;

    /**
     * Altura do aluno no momento da avaliação física.
     * Este valor é armazenado como um número de ponto flutuante.
     */
    private double altura;

    /**
     * Método que é chamado automaticamente antes de a avaliação ser persistida no banco de dados.
     * Ele define o campo `dataDaAvaliacao` como a data e hora atuais no momento da criação da avaliação.
     */
    @PrePersist  // A anotação indica que o método será chamado antes da inserção no banco
    protected void onCreate() {
        this.dataDaAvaliacao = LocalDateTime.now();  // Atribui o valor da data e hora atual
    }
}
