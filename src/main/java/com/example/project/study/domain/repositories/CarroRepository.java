package com.example.project.study.domain.repositories;

import com.example.project.study.domain.model.carro.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    void deleteAllByNomeIsNullAndMarcaIsNullAndAnoIsNull();

    Carro findByUuid(UUID uuid);
}