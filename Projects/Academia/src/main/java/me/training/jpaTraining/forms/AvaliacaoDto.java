package me.training.jpaTraining.forms;

import java.time.LocalDateTime;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Classe DTO (Data Transfer Object) que representa uma avaliação física de um aluno.
 * Usada para transferir dados de entrada durante a criação ou atualização de uma avaliação.
 */
public record AvaliacaoDto(
    
    /**
     * ID do aluno ao qual a avaliação pertence.
     * O ID do aluno é essencial para associar a avaliação ao aluno correto.
     */
    @NotNull(message = "O ID do aluno não pode ser nulo.")  // Garante que o ID do aluno seja fornecido
    Long alunoid,
    
    /**
     * A data e hora da avaliação.
     * Esta data indica quando a avaliação foi realizada e não pode ser nula.
     */
    @NotNull(message = "A data da avaliação não pode ser nula.")  // Garante que a data da avaliação seja fornecida
    LocalDateTime dataDaAvaliacao,
    
    /**
     * O peso do aluno na avaliação, em quilogramas.
     * O peso deve ser um valor positivo.
     */
    @NotNull(message = "O peso não pode ser nulo.")  // Garante que o peso seja fornecido
    @Min(value = 0, message = "O peso deve ser um valor positivo.")  // Garante que o peso seja maior ou igual a 0
    double peso,
    
    /**
     * A altura do aluno na avaliação, em metros.
     * A altura deve ser um valor positivo.
     */
    @NotNull(message = "A altura não pode ser nula.")  // Garante que a altura seja fornecida
    @Min(value = 0, message = "A altura deve ser um valor positivo.")  // Garante que a altura seja maior ou igual a 0
    double altura
) {}


