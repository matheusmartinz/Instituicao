package com.example.project2.study.domain.model.Instituicao.Financeiro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MensalidadeRepository extends JpaRepository<Mensalidade, Long> {
    Mensalidade findByUuid(UUID uuidMensalidade);
}
