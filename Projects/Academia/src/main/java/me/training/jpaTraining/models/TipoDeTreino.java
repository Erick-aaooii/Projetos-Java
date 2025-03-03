package me.training.jpaTraining.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum TipoDeTreino {

    Natação("Natação", "Treinamento realizado na água, que visa melhorar a resistência cardiovascular e a força muscular."),
    Musculação("Musculação", "Treinamento realizado com pesos ou equipamentos, visando o aumento da força muscular e o crescimento muscular."),
    Calistenia("Calistenia", "Treinamento com o peso do próprio corpo, focado em aumentar a força, flexibilidade e resistência."),
    Corrida("Corrida", "Treinamento de resistência, onde o objetivo é melhorar a capacidade cardiovascular e a resistência muscular, realizado por meio de corrida."),
    Ciclismo("Ciclismo", "Atividade física que envolve a pedalada em uma bicicleta, visando melhorar a resistência cardiovascular e muscular."),
    Yoga("Yoga", "Prática que envolve posturas físicas, respiração e meditação, com o objetivo de melhorar a flexibilidade, equilíbrio e saúde mental."),
    Pilates("Pilates", "Treinamento que combina força e flexibilidade, focado na estabilização do core e no alongamento muscular."),
    Boxe("Boxe", "Treinamento de combate, que foca no aumento da força física, agilidade, resistência e habilidades de defesa."),
    HIIT("HIIT", "Treinamento de alta intensidade e curta duração, visando maximizar a queima de calorias e melhorar a capacidade cardiovascular em pouco tempo."),
    CrossFit("CrossFit", "Treinamento intenso, que mistura levantamento de peso, ginástica e exercícios cardiovasculares com o objetivo de melhorar o condicionamento físico geral.");

    private final String nome;
    private final String descricao;

    // Método para buscar o tipo de treino a partir do nome
    public static TipoDeTreino fromNome(String nome) {
        for (TipoDeTreino tipo : TipoDeTreino.values()) {
            if (tipo.nome.equalsIgnoreCase(nome)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de treino não encontrado: " + nome);
    }
}
