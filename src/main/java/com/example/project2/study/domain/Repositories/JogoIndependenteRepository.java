package com.example.project2.study.domain.Repositories;

import com.example.project2.study.domain.model.Empresa.JogoIndependente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoIndependenteRepository extends JpaRepository<JogoIndependente, Long> {
}
