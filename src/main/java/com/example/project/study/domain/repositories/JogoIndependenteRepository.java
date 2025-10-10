package com.example.project.study.domain.repositories;

import com.example.project.study.domain.model.empresa.JogoIndependente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoIndependenteRepository extends JpaRepository<JogoIndependente, Long> {
}
