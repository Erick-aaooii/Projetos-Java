package me.training.jpaTraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import me.training.jpaTraining.models.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
