package me.training.jpaTraining.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import me.training.jpaTraining.forms.AvaliacaoDto;
import me.training.jpaTraining.models.Aluno;
import me.training.jpaTraining.models.AvaliacaoFisica;
import me.training.jpaTraining.repository.AlunoRepository;
import me.training.jpaTraining.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired 
    private AlunoRepository alunoRepository;

    public AvaliacaoFisica create(AvaliacaoDto form) {
        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
    
        Aluno aluno = alunoRepository.findById(form.alunoid())
        .orElseThrow(() -> new RuntimeException("Aluno não encontrado com ID: " + form.alunoid()));

    
        avaliacaoFisica.setAluno(aluno);
        avaliacaoFisica.setPeso(form.peso());
        avaliacaoFisica.setAltura(form.altura());
    
        return avaliacaoRepository.save(avaliacaoFisica);
    }

    public AvaliacaoFisica get(long id) {
        return avaliacaoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar uma Avaliação com esse id") );
    }

    public List<AvaliacaoFisica> list() {
        return avaliacaoRepository.findAll();

    }
}