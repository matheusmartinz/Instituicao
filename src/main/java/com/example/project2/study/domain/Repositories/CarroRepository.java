package com.example.project2.study.domain.Repositories;

import com.example.project2.study.domain.model.Carro.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    void deleteAllByNomeIsNullAndMarcaIsNullAndAnoIsNull();

    Carro findByUuid(UUID uuid);
}