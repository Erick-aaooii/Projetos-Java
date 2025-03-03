package me.training.jpaTraining.forms;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MatriculaDto(
    @NotNull(message = "O ID do Aluno é necessário")  // Garante que o alunoId não seja nulo
    Long alunoId,

    @NotNull(message = "O tipo de treino é necessário")  // Garante que o tipo de treino não seja nulo
    String treino,

    @NotNull(message = "O valor da matrícula é necessário")  // Garante que o valor não seja nulo
    @Positive(message = "O valor da matrícula deve ser maior que zero")  // Garante que o valor seja positivo
    BigDecimal valor
) {}
