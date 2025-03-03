package me.training.jpaTraining.forms;

import java.time.LocalDate;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Classe DTO (Data Transfer Object) que representa um aluno, usada para transferir dados
 * de entrada no processo de criação ou atualização de um aluno.
 * Este DTO contém as validações necessárias para garantir que os dados sejam válidos.
 */
public record AlunoDto(
    /**
     * Valida que o nome não pode estar em branco e deve ter entre 3 e 35 caracteres.
     * A mensagem será retornada caso a validação falhe.
     */
    @NotBlank(message = "Nome não pode estar em branco")
    @Size(min = 3, max = 35, message = "Nome deve ter entre 3 a 35 caracteres")
    String nome,

    /**
     * Valida que o CPF não pode estar em branco e que deve estar no formato de cpfs com .
     * A validação usa uma expressão regular para garantir o formato correto.
     */
    @NotBlank(message = "Cpf não pode estar em branco")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato XXX.XXX.XXX-XX")
    String cpf,

    /**
     * Valida que o bairro não pode estar em branco. 
     * Esta validação garante que a informação de bairro seja fornecida.
     */
    @NotBlank(message = "Bairro é obrigatório") 
    String bairro,

    /**
     * Valida que a idade é obrigatória e que deve ser pelo menos 12 anos.
     * Utiliza a anotação @Min para garantir que a idade não seja menor que 12 anos.
     * A mensagem será retornada se a idade for inferior a 12.
     */
    @NotNull(message = "A idade é obrigatória")
    @Min(value = 12, message = "Não aceitamos pessoas com menos de 13 anos")
    Integer idade,

    /**
     * Valida que a data de nascimento não pode ser nula. 
     * Garante que a data de nascimento seja fornecida.
     */
    @NotNull(message = "A data de Nascimento é obrigatória")
    LocalDate dataDeNascimento,

    /**
     * Valida que a imagem não pode estar em branco. 
     * Esta validação exige que uma foto do aluno seja fornecida.
     */
    
    List<MultipartFile> img) {}