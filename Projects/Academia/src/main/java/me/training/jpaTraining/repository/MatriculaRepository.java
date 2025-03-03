package me.training.jpaTraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import me.training.jpaTraining.models.Matricula;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

}
