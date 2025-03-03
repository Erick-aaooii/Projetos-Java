package me.training.jpaTraining.repository;

import org.springframework.stereotype.Repository;
import me.training.jpaTraining.models.AvaliacaoFisica;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoFisica, Long>  {
    
}
