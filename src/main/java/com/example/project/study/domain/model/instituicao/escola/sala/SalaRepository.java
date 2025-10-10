package com.example.project.study.domain.model.instituicao.escola.sala;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
    Sala findByUuid(UUID uuid);
}
